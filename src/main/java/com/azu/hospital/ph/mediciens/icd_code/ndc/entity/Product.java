package com.azu.hospital.ph.mediciens.icd_code.ndc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    private String productNDC;
    private String nonProprietaryName;
    private String dosageFormName;
    private String substanceName;
    private BigDecimal activeNumeratorStrength;
    private String activeIngredUnit;

    public Product() {
    }


    public Product(String productNDC, String nonProprietaryName, String dosageFormName, BigDecimal activeNumeratorStrength, String activeIngredUnit) {
        this.productNDC = productNDC;
        this.nonProprietaryName = nonProprietaryName;
        this.dosageFormName = dosageFormName;
        this.activeNumeratorStrength = activeNumeratorStrength;
        this.activeIngredUnit = activeIngredUnit;
    }
    public String getName() {
        return this.nonProprietaryName;
    }

    @Override
    public String toString() {
        return "Product [productNDC=" + productNDC + ", nonProprietaryName=" + nonProprietaryName
                + ", dosageFormName=" + dosageFormName + ", substanceName=" + substanceName
                + ", activeNumeratorStrength=" + activeNumeratorStrength
                + ", activeIngredUnit=" + activeIngredUnit + "]";
    }

}