package com.azu.hospital.radiology.radiology_bill_handler.dto;

import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RadiologyBillMapper implements Function<RadiologyBillHandler, RadiologyBillDto> {

    @Override
    public RadiologyBillDto apply(RadiologyBillHandler radiologyBillHandler) {
        return new RadiologyBillDto(
                radiologyBillHandler.getId(),
                radiologyBillHandler.getNote(),
                radiologyBillHandler.getPrice(),
                radiologyBillHandler.getType()
        );
    }
}
