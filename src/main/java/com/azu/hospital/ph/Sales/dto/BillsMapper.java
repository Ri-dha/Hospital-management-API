package com.azu.hospital.ph.Sales.dto;

import com.azu.hospital.ph.Sales.entity.SoldBill;

public interface BillsMapper {

    SoldBillDto toDto(SoldBill soldBill);
    SoldBill toEntity(SoldBillDto dto);
}
