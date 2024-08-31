package com.bmc.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {

    private String branchAddress;

    private String accountType;

    private Long accountNumber;
}
