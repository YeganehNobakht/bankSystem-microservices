package com.bmc.accounts.service.impl;

import com.bmc.accounts.Constants.AccountsConstants;
import com.bmc.accounts.dto.CustomerDto;
import com.bmc.accounts.entity.Accounts;
import com.bmc.accounts.entity.Customer;
import com.bmc.accounts.mapper.CustomerMapper;
import com.bmc.accounts.repository.AccountsRepository;
import com.bmc.accounts.repository.CustomerRepository;
import com.bmc.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        return Accounts
                .builder()
                .customerId(customer.getCustomerId())
                .accountNumber(1000000000L + new Random().nextInt(900000000))
                .accountType(AccountsConstants.SAVINGS)
                .branchAddress(AccountsConstants.ADDRESS)
                .build();
    }
}
