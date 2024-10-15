package com.azu.hospital.ph.mediciens.icd_code.ndc.dto;

import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DrugTypeDtoMapper implements Function<Product, DrugTypeDto> {

    @Override
    public DrugTypeDto apply(Product product){
        return new DrugTypeDto(
                product.getDosageFormName()
        );
    }
}
