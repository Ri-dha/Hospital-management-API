package com.azu.hospital.newPh.Medicins.Dto;

import com.azu.hospital.newPh.Medicins.entity.Medicines;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MedicinesDtoMapper implements Function<Medicines, MedicinesDto>{
    @Override
    public MedicinesDto apply(Medicines medicines) {
        return new MedicinesDto(
                medicines.getId(),
                medicines.getName(),
                medicines.getBarCode(),
                medicines.getPrice(),
                medicines.getQuantity(),
                medicines.getCreatedAt(),
                medicines.getUpdateAt()
        );
    }
}
