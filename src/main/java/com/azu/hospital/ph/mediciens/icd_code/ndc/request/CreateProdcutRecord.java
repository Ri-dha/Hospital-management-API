package com.azu.hospital.ph.mediciens.icd_code.ndc.request;

import java.math.BigDecimal;

public record CreateProdcutRecord(


        String productNDC,
        String nonProprietaryName,
        String dosageFormName,
        BigDecimal activeNumeratorStrength,
        String activeIngredUnit
) {
}
