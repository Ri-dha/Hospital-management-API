package com.azu.hospital.ph.mediciens.icd_code.ndc.dto;

import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductDtoMapper implements Function<Product, ProductDto> {
    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getProductNDC()
        );
    }

}
