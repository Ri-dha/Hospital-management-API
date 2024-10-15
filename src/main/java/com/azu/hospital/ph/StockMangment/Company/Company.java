package com.azu.hospital.ph.StockMangment.Company;

import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_id_seq",
            sequenceName = "company_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_id_seq"
    )
    private Integer companyId;
    @NotNull(message = "Company Name require")
    @NotEmpty(message = "Company Name require")
    @NotBlank(message = "Company Name require")
    private String companyName;
    @Email(
            message = "Email not Valid",
            regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            flags =  {Pattern.Flag.MULTILINE}
    )
    private String companyEmail;
    @Pattern(regexp = "\\d+", message = "Phone number should only contain digits")
    private String companyPhoneNumber;
    private String companyAddress;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Offer> offers;


    public Company() {
    }

    public Company(Integer companyId, String companyName, String companyEmail, String companyPhoneNumber, String companyAddress, List<Offer> offers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.offers = offers;
    }

    public Company(String companyName, String companyEmail, String companyPhoneNumber, String companyAddress, List<Offer> offers) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.offers = offers;
    }

    public Company(String companyName, String companyEmail, String companyPhoneNumber, String companyAddress) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
    }
}
