package com.azu.hospital.ph.StockMangment.Company;

import jakarta.validation.constraints.*;

public record CompanyUpdateRequest(


        @NotNull(message = "Company Name require")
        @NotEmpty(message = "Company Name require")
        @NotBlank(message = "Company Name require")
        String companyName,
        @Email(
                message = "Email not Valid",
                regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
                flags =  {Pattern.Flag.MULTILINE}
        )
        String companyEmail,
        @Pattern(regexp = "\\d+", message = "Phone number should only contain digits")
        String companyPhoneNumber,
        String companyAddress
) {
}
