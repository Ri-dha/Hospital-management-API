package com.azu.hospital.ph.StockMangment.AddPharmacy;

import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Pharmacy")
public class Pharmacy {

    @Id
    @SequenceGenerator(
            name = "pharmacy_id_seq",
            sequenceName = "pharmacy_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pharmacy_id_seq"
    )
    private Integer pharmacyId;
    @NotEmpty(message = "Pharmacy Name Should Not Be empty")
    @NotBlank(message = "Pharmacy Name Should Not Be Blank")
    @Size(min = 4, max = 50, message = "Pharmacy Name Should Not Be Less than 4 Letters and dont more than 50 Letters")
    @NotNull(message = "Pharmacy Name Should Not Be Null")
    private String pharmacyName;
    private String responsiblePharmacist;
    private String pharmacyEmail;
    private String pharmacyPhone;
    private String pharmacyAddress;

    // TODO: 11/10/2023 Edit Bills Relationship

//    @OneToMany()
//    private List<StockBill> bills;



    public Pharmacy() {
    }

    public Pharmacy(Integer pharmacyId, String pharmacyName, String responsiblePharmacist, String pharmacyEmail, String pharmacyPhone, String pharmacyAddress, List<StockBill> bills) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.responsiblePharmacist = responsiblePharmacist;
        this.pharmacyEmail = pharmacyEmail;
        this.pharmacyPhone = pharmacyPhone;
        this.pharmacyAddress = pharmacyAddress;
//        this.bills = bills;
    }

    public Pharmacy(String pharmacyName, String responsiblePharmacist, String pharmacyEmail, String pharmacyPhone, String pharmacyAddress) {
        this.pharmacyName = pharmacyName;
        this.responsiblePharmacist = responsiblePharmacist;
        this.pharmacyEmail = pharmacyEmail;
        this.pharmacyPhone = pharmacyPhone;
        this.pharmacyAddress = pharmacyAddress;
    }

    public Pharmacy(String pharmacyName, String responsiblePharmacist, String pharmacyEmail, String pharmacyPhone, String pharmacyAddress, List<StockBill> bills) {
        this.pharmacyName = pharmacyName;
        this.responsiblePharmacist = responsiblePharmacist;
        this.pharmacyEmail = pharmacyEmail;
        this.pharmacyPhone = pharmacyPhone;
        this.pharmacyAddress = pharmacyAddress;
//        this.bills = bills;
    }

    public Pharmacy(Integer pharmacyId, String pharmacyName, String pharmacyAddress, String pharmacyEmail, String pharmacyPhone, String responsiblePharmacist) {
       this.pharmacyId = pharmacyId;
       this.pharmacyName = pharmacyName;
       this.responsiblePharmacist = responsiblePharmacist;
        this.pharmacyEmail = pharmacyEmail;
        this.pharmacyPhone = pharmacyPhone;
        this.pharmacyAddress = pharmacyAddress;
    }



//    public List<StockBill> getBills() {
//        return bills;
//    }
//
//    public void setBills(List<StockBill> bills) {
//        this.bills = bills;
//    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getResponsiblePharmacist() {
        return responsiblePharmacist;
    }

    public void setResponsiblePharmacist(String responsiblePharmacist) {
        this.responsiblePharmacist = responsiblePharmacist;
    }

    public String getPharmacyEmail() {
        return pharmacyEmail;
    }

    public void setPharmacyEmail(String pharmacyEmail) {
        this.pharmacyEmail = pharmacyEmail;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }
}
