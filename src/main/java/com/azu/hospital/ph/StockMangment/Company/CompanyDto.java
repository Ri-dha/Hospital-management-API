package com.azu.hospital.ph.StockMangment.Company;

import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private Integer companyId;

    @NotNull(message = "Company Name is required")
    @NotEmpty(message = "Company Name is required")
    @NotBlank(message = "Company Name is required")
    private String companyName;

    @Email(message = "Email not Valid", regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String companyEmail;

    @Pattern(regexp = "\\d+", message = "Phone number should only contain digits")
    private String companyPhoneNumber;

    private String companyAddress;

    private List<Offer> offers;

    public CompanyDto(Integer companyId, String companyName, String companyEmail, String companyPhoneNumber, String companyAddress, List<Offer> offers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.offers = offers;
    }

    public CompanyDto() {

    }
}
