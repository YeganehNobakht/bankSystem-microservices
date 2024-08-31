package com.bmc.accounts.service.impl;

import com.bmc.accounts.dto.CustomerDto;
import com.bmc.accounts.repository.AccountsRepository;
import com.bmc.accounts.repository.CustomerRepository;
import com.bmc.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IAccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
