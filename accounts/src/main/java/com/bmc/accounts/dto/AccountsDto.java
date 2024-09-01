package com.bmc.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Acconts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Name of the Branch"
    )
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;

    @Schema(
            description = "Account type of the Customer", example = "Savings"
    )
    @NotEmpty(message = "AccountType can not be a null or empty")
    private String accountType;

    @Schema(
            description = "Account number of the Customer"
    )
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;
}
