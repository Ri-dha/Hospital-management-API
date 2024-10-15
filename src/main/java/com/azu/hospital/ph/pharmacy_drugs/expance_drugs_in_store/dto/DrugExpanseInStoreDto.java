package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dto;

import java.math.BigDecimal;

public record DrugExpanseInStoreDto(
        Long expanseId,
        Integer quantity,
        Long drugId,
        String drugTradeName,
        String drugScientificName,
        String drugType,
        BigDecimal drugBuyingPrice,
        String typeOfCurrency,
        String barcode
) {


}
