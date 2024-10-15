package com.azu.hospital.ph.mediciens.icd_code.ndc.dao;

import com.azu.hospital.ph.mediciens.icd_code.ndc.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR lower(p.nonProprietaryName) LIKE lower(concat('%', :name, '%')))")
    Page<Product> findByNonProprietaryNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR lower(p.nonProprietaryName) LIKE lower(concat('%', :name, '%'))) AND (:ndc IS NULL OR lower(p.productNDC) LIKE lower(concat('%', :ndc, '%')))")
    Page<Product> findByproductNDCOrNonProprietaryNameContainingIgnoreCase(String name, String ndc, Pageable pageable);

}
