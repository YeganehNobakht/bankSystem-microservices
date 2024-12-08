package com.bmc.accounts.dto;

/**
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */

//we want whenever an account is created, triggering an event into the message broker.
public record AccountsMsgDto(Long accountNumber, String name, String email, String mobileNumber) {
}
