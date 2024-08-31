package com.bmc.accounts.service;

import com.bmc.accounts.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
}
