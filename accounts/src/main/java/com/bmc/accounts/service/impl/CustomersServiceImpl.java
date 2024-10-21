package com.bmc.accounts.service.impl;

import com.bmc.accounts.dto.AccountsDto;
import com.bmc.accounts.dto.CardsDto;
import com.bmc.accounts.dto.CustomerDetailsDto;
import com.bmc.accounts.dto.LoansDto;
import com.bmc.accounts.entity.Accounts;
import com.bmc.accounts.entity.Customer;
import com.bmc.accounts.exception.ResourceNotFoundException;
import com.bmc.accounts.mapper.AccountsMapper;
import com.bmc.accounts.mapper.CustomerMapper;
import com.bmc.accounts.repository.AccountsRepository;
import com.bmc.accounts.repository.CustomerRepository;
import com.bmc.accounts.service.CustomersService;
import com.bmc.accounts.service.client.CardsFeignClient;
import com.bmc.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (null != loansDtoResponseEntity)
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (null != cardsDtoResponseEntity)
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
