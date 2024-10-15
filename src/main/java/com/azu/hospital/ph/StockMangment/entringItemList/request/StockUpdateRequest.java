package com.azu.hospital.ph.StockMangment.entringItemList.request;
import java.math.BigDecimal;


public record StockUpdateRequest(
        String pharmacyName,
        String supplierDetails,
        BigDecimal billNumber,
        String billDate,
        String billEntryDate,
        BigDecimal billTotalPrice,
        BigDecimal billTotalDiscount,
        BigDecimal billTotalDebts,
        BigDecimal billTotalMoneyPaid,
        BigDecimal billTotalAfterDiscount,
        BigDecimal totalRestMoney,
        String billDescriptions
) {
}
