package com.azu.hospital.ph.Sales.dto;


import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class SoldBillDto {
    private Long billId;
    private Instant dateOfPurchase;
    private BigDecimal fullPrice;
    private List<SoldItemsDto> itemsList;
    private Instant createAt;
    private Instant updateAt;
    private long patientId;
    private String patientName;

    public SoldBillDto() {

    }

    public SoldBillDto(Long billId, Instant dateOfPurchase,
                       BigDecimal fullPrice, List<SoldItemsDto> itemsList,
                       Instant createAt, Instant updateAt, long patientId, String patientName) {
        this.billId = billId;
        this.dateOfPurchase = dateOfPurchase;
        this.fullPrice = fullPrice;
        this.itemsList = itemsList;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.patientId = patientId;
        this.patientName = patientName;
    }


    public SoldBillDto(Long billId, Instant dateOfPurchase, BigDecimal fullPrice) {
        this.billId = billId;
        this.dateOfPurchase = dateOfPurchase;
        this.fullPrice = fullPrice;
    }
}