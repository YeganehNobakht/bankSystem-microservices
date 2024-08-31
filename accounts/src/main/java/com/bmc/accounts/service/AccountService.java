package com.bmc.accounts.service;

import com.bmc.accounts.dto.CustomerDto;

public interface AccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
