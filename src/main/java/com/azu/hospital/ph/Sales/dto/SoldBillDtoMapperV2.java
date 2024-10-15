package com.azu.hospital.ph.Sales.dto;


import com.azu.hospital.ph.Sales.entity.SoldBill;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SoldBillDtoMapperV2 implements Function<SoldBill, SoldBillDto> {
    @Override
    public SoldBillDto apply(SoldBill soldBill) {
        return new SoldBillDto(
                soldBill.getBillId(),
                soldBill.getDateOfPurchase(),
                soldBill.getBillTotalPrice()
        );
    }
}
