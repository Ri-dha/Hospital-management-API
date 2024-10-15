package com.azu.hospital.ph.Sales.dto;

import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class SoldBillWithTotalPrice {

    private Page<SoldBillDto> soldItemsDto;

    private BigDecimal allBillTotalPrice;


    public SoldBillWithTotalPrice() {
    }

    public SoldBillWithTotalPrice(Page<SoldBillDto> soldItemsDto, BigDecimal allBillTotalPrice) {
        this.soldItemsDto = soldItemsDto;
        this.allBillTotalPrice = allBillTotalPrice;
    }
}
