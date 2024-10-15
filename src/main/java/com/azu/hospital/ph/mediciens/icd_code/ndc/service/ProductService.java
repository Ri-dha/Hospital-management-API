package com.azu.hospital.ph.mediciens.icd_code.ndc.service;

import com.azu.hospital.ph.mediciens.icd_code.ndc.dao.ProductDao;
import com.azu.hospital.ph.mediciens.icd_code.ndc.dto.ProductDto;
import com.azu.hospital.ph.mediciens.icd_code.ndc.dto.ProductDtoMapper;
import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import com.azu.hospital.ph.mediciens.icd_code.ndc.request.CreateProdcutRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductDao dao;

    public ProductService( ProductDtoMapper mapper, ProductDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }



    public Page<ProductDto> searchProducts(String name, Pageable pageable) {
        return dao.searchProducts(name, pageable).map(mapper);
    }

    public Page<ProductDto> getProducts(Pageable pageable) {
            return dao.getProducts(pageable).map(mapper);
    }

    public Page<ProductDto> findByproductNDCOrNonProprietaryNameContainingIgnoreCase(String name, String ndc, Pageable pageable) {
        return dao.findByproductNDCOrNonProprietaryNameContainingIgnoreCase(name, ndc, pageable).map(mapper);
    }


    public void createProduct(CreateProdcutRecord request) {
        Product product = new Product(
                request.productNDC(),
                request.nonProprietaryName(),
                request.dosageFormName(),
                request.activeNumeratorStrength(),
                request.activeIngredUnit()
        );

        dao.createProduct(product);
    }


}
