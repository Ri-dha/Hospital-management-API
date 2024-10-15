package com.azu.hospital.ph.StockMangment.Supplier;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SupplierDao {

    void selectAllSuppliers(Pageable pageable);
    Optional<Supplier>findSupplierById(Integer supplierId);
    void insertSupplier(Supplier supplier);
    void updateSupplier(Supplier update);
    void deleteSupplierById(Integer supplierId);
    boolean supplierExistsByEmail(String supplierEmail);
    boolean supplierExistsById(Integer supplierId);
    Supplier getSuppliersBySupplierName(String supplierName);
}
