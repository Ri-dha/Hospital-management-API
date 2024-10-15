package com.azu.hospital.ph.StockMangment.entringItemList.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record StockRegistrationRequest(
        @NotNull(message = "Pharmacy Name Required")
        @NotBlank(message = "Pharmacy Name Required")
        @NotEmpty(message = "Pharmacy Name Required")
        String pharmacy,

        @NotNull(message = "Supplier Name Required")
        @NotBlank(message = "Supplier Name Required")
        @NotEmpty(message = "Supplier Name Required")
        String supplierDetails,

        @NotNull(message = "List Number Required")
        BigDecimal billNumber,

        @NotNull(message = "List Date Required")
        @NotBlank(message = "List Date Required")
        @NotEmpty(message = "List Date Required")
        String billDate,

        @NotNull(message = "List Entry Date Required")
        @NotBlank(message = "List Entry Date Required")
        @NotEmpty(message = "List Entry Date Required")
        String billEntryDate,

        @NotNull(message = "Price Required")
        BigDecimal billTotalPrice,

        BigDecimal billTotalDiscount,

        @NotNull(message = "Total Debts Required")
        BigDecimal billTotalDebts,

        @NotNull(message = "Money Paid Required")
        BigDecimal billTotalMoneyPaid,

        @NotNull(message = "Total After Discount require")
        BigDecimal billTotalAfterDiscount,

        @NotNull(message = "Rest Money  Required")
        BigDecimal totalRestMoney,

        @NotNull(message = "Location Name Required")
        @NotBlank(message = "Location Name Required")
        @NotEmpty(message = "Location Name Required")
         String location,

        String mobile,
        String billDescriptions
) {
}
