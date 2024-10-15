package com.azu.hospital.ph.StockMangment.Supplier;

import com.azu.hospital.ph.StockMangment.Offers.Dao.Offer;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Entity
@Table(
        name = "supplier",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "supplier_supplierEmail_unique",
                        columnNames = "supplierEmail"

                )
        }
)
public class Supplier {

    @Id
    @SequenceGenerator(
            name = "supplier_id_seq",
            sequenceName = "supplier_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_id_seq"
    )
    private Integer supplierId;
    @Column(
            nullable = false
    )
    private String supplierName;
    @Column(
            nullable = false,
            unique = true
    )
    private String supplierEmail;
    @Column(
            nullable = false
    )
    private String supplierPhone;
    @Column(
            nullable = false
    )
    private String address;
// TODO: 11/10/2023 Edit Bills Relationship

//    @OneToMany()
//    private List<StockBill> bills;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Offer> offers;

    // TODO: 7/7/2023 RelationShip with Buying Table
    public Supplier() {
    }


    public Supplier(Integer supplierId, String supplierName, String supplierEmail, String supplierPhone, String address, List<StockBill> bills) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
        this.address = address;
//        this.bills = bills;
    }

    public Supplier(Integer supplierId, String supplierName, String supplierEmail, String supplierPhone, String address) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
        this.address = address;
    }

    public Supplier(String supplierName, String supplierEmail, String supplierPhone, String address) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
        this.address = address;
    }

    public Supplier(String supplierName, String supplierEmail, String supplierPhone, String address, List<StockBill> bills) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
        this.address = address;
//        this.bills = bills;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

//    public List<StockBill> getBills() {
//        return bills;
//    }
//
//    public void setBills(List<StockBill> bills) {
//        this.bills = bills;
//    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
