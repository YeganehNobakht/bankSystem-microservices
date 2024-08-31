package com.bmc.accounts.mapper;

import com.bmc.accounts.dto.AccountsDto;
import com.bmc.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccounts(Accounts accounts , AccountsDto accountsDto){
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
    public static Accounts mapToAccounts(AccountsDto accountsDto , Accounts accounts){
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
