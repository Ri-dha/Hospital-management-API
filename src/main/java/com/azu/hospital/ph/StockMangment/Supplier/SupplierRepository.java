package com.azu.hospital.ph.StockMangment.Supplier;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {


    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Supplier s WHERE s.supplierEmail = :supplierEmail")
    boolean existsBySupplierEmail(@Param("supplierEmail") String supplierEmail);

    @Query("SELECT s FROM Supplier s WHERE " +
            "(:supplierName IS NULL OR LOWER(s.supplierName) LIKE %:supplierName%) " +
            "AND (:supplierEmail IS NULL OR LOWER(s.supplierEmail) LIKE %:supplierEmail%)")
    Page<Supplier> findBySupplierSortedOrderBy(
            @Param("supplierName") String supplierName,
            @Param("supplierEmail") String supplierEmail,
            Pageable pageable);
    @Query("SELECT s FROM Supplier s WHERE s.supplierName = :supplierName")
    Supplier getSuppliersBySupplierName(String supplierName);
}
