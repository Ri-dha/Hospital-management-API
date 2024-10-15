package com.azu.hospital.ph.Sales.Request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SoldBillUpdateRequest {

    private BigDecimal discount;
    private BigDecimal priceAfterDiscount;
    private BigDecimal billTotalPrice;
    private Integer quantity;

    public SoldBillUpdateRequest() {
    }

    public SoldBillUpdateRequest( BigDecimal discount,
                                 BigDecimal priceAfterDiscount, BigDecimal billTotalPrice, Integer quantity) {
        this.discount = discount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.billTotalPrice = billTotalPrice;
        this.quantity = quantity;
    }
}
