package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.mediciens.type.MedicineType;
import com.azu.hospital.utils.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;


@Getter
@Setter
public class DrugIsBackDto {

    private Long drugId;
    private String drugTradeName;
    private String drugScientificName;
    private String drugCompany;
    private String dose;
    private String type;
    private Integer quantity;
    private String drugBonus;
    private String description;
    private String expDate;
    private String proDate;
    private String barcode;
    private String ndcNumber;
    private BigDecimal price;
    private BigDecimal drugSellingPrice;
    private Currency typOfCurrency;
    private BigDecimal exchange;
    private boolean isBack;
    private boolean isDrugExists;
    private String drugImageUrls;
    private Long billId; // drug billId
    private Instant updateAt; // last update At for is back
    private Long patientId; // patient or customer idpatient; // this for customer and patient
    private String note;// patient
    private Long soldBillId; // from customer

    public DrugIsBackDto() {
    }

    public DrugIsBackDto(Long id, String drugTradeName, String drugScientificName,
                         String itemCompany, String dose, String type,
                         Integer quantity, String drugBonus, String description,
                         String expDate, String proDate, String barcode, String ndcNumber,
                         BigDecimal price, BigDecimal drugSellingPrice, Currency typeOfCurrency,
                         BigDecimal exchange, Boolean isBack, Boolean isDrugExist,
                         String imageUrls, Long billId, Instant updateAt,
                         Long patientId, String note, Long billListId) {

        this.drugId = id;
        this.drugTradeName = drugTradeName;
        this.drugScientificName = drugScientificName;
        this.drugCompany = itemCompany;
        this.dose = dose;
        this.type = type;
        this.quantity = quantity;
        this.drugBonus = drugBonus;
        this.description = description;
        this.expDate = expDate;
        this.proDate = proDate;
        this.barcode = barcode;
        this.ndcNumber = ndcNumber;
        this.price = price;
        this.drugSellingPrice = drugSellingPrice;
        this.typOfCurrency = typeOfCurrency;
        this.exchange = exchange;
        this.isBack = isBack;
        this.isDrugExists = isDrugExist;
        this.drugImageUrls = imageUrls;
        this.billId = billId;
        this.updateAt = updateAt;
        this.patientId = patientId;
        this.note = note;
        this.soldBillId = billListId;

    }
}
