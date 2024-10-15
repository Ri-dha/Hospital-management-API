package com.azu.hospital.ph.Sales.dto;


import com.azu.hospital.ph.Sales.customer.dto.CustomerDto;
import com.azu.hospital.ph.Sales.customer.dto.CustomerDtoMapper;
import com.azu.hospital.ph.Sales.customer.entity.Customer;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDto;
import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDtoMapper;
import com.azu.hospital.utils.enums.Gender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;


@Service
public class SoldBillDtoMapper implements Function<SoldBill, SoldBillDto> {


    private final SoldItemsDtoMapper soldItemsDtoMapper;

    public SoldBillDtoMapper(SoldItemsDtoMapper soldItemsDtoMapper) {
        this.soldItemsDtoMapper = soldItemsDtoMapper;
    }
    @Override
    public SoldBillDto apply(SoldBill soldBill) {
        List<SoldItemsDto> itemsDto = soldBill.getItemsList()
                .stream()
                .map(soldItemsDtoMapper)
                .toList();


        Instant createAt = null;
        Instant updateAt = null;

        if (soldBill.getCreateAt() != null) {
            createAt = soldBill.getCreateAt();
        }
        if (soldBill.getUpdateAt() != null) {
            updateAt = soldBill.getUpdateAt();
        }


        return new SoldBillDto(
                soldBill.getBillId(),
                soldBill.getDateOfPurchase(),
                soldBill.getBillTotalPrice(),
                itemsDto,
                createAt,
                updateAt,
                soldBill.getPatient().getId(),
                soldBill.getPatient().getPatientData().getFullName()


        );
    }

}
