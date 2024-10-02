package com.bmc.accounts.service;

import com.bmc.accounts.dto.CustomerDetailsDto;

public interface CustomersService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
