package com.azu.hospital.ph.StockMangment.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("suppliersJpa")
@Qualifier("suppliersJpa")
public class SupplierJPADataAccess implements SupplierDao {

    private  final SupplierRepository supplierRepository;

    public SupplierJPADataAccess(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void selectAllSuppliers(Pageable pageable) {
        supplierRepository.findAll(pageable);
    }

    @Override
    public Optional<Supplier> findSupplierById(Integer supplierId) {
        return supplierRepository.findById(supplierId);
    }

    @Override
    public void insertSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void updateSupplier(Supplier update) {
        supplierRepository.save(update);
    }

    @Override
    public void deleteSupplierById(Integer supplierId) {
        supplierRepository.deleteById(supplierId);

    }

    @Override
    public boolean supplierExistsByEmail(String supplierEmail) {
        return supplierRepository.existsBySupplierEmail(supplierEmail);
    }

    @Override
    public boolean supplierExistsById(Integer supplierId) {
        return supplierRepository.existsById(supplierId);
    }

    @Override
    public Supplier getSuppliersBySupplierName(String supplierName) {
        return supplierRepository.getSuppliersBySupplierName(supplierName);
    }
}
