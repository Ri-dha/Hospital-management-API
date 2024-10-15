package com.azu.hospital.ecg.ecgbill.dto;

import com.azu.hospital.ecg.ecgbill.entity.EcgBill;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class EcgBillMapper implements Function<EcgBill,EcgBillDto> {


    @Override
    public EcgBillDto apply(EcgBill ecgBill) {
        return new EcgBillDto(
                ecgBill.getId(),
                ecgBill.getNote(),
                ecgBill.getPrice()
        );
    }
}
