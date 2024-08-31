package com.bmc.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {

    private Long customerId;

    private String accountType;

    private String mobileNumber;
}
