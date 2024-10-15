package com.azu.hospital.ph.mediciens.icd_code.ndc.dao;

import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDataAccessJpa")
public class ProductDataAccess implements ProductDao {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDataAccess(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void createProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public Page<Product> searchProducts(String name, Pageable pageable) {
        return productRepository.findByNonProprietaryNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByproductNDCOrNonProprietaryNameContainingIgnoreCase(String name, String ndc, Pageable pageable) {
        return productRepository.findByproductNDCOrNonProprietaryNameContainingIgnoreCase(name, ndc, pageable);
    }
}
