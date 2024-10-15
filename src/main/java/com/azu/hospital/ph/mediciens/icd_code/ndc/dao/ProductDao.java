package com.azu.hospital.ph.mediciens.icd_code.ndc.dao;

import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDao {

    void createProduct(Product product);
    void createProducts(List<Product> products);
    Page<Product> searchProducts(String name, Pageable pageable);
    Page<Product> getProducts(Pageable pageable);

    Page<Product> findByproductNDCOrNonProprietaryNameContainingIgnoreCase(String name,String ndc, Pageable pageable);
}
