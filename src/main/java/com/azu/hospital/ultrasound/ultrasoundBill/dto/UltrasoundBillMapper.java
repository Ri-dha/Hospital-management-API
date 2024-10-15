package com.azu.hospital.ultrasound.ultrasoundBill.dto;


import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UltrasoundBillMapper implements Function<UltrasoundBill,UltrasoundBillDto> {
    @Override
    public UltrasoundBillDto apply(UltrasoundBill ultrasoundBill) {
        return new UltrasoundBillDto(
                ultrasoundBill.getId(),
                ultrasoundBill.getNote(),
                ultrasoundBill.getPrice(),
                ultrasoundBill.getType()
        );
    }
}
