package com.bmc.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Masi"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(max = 30, min = 5, message = "Name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "m.yeganehnobakht@gmail.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",
            example = "1234567896"
    )
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details for the customer"
    )
    private AccountsDto accountsDto;
}
