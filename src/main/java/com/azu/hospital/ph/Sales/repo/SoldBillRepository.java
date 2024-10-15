package com.azu.hospital.ph.Sales.repo;

import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface SoldBillRepository extends JpaRepository<SoldBill, Long> {

    @Query("SELECT s FROM SoldBill s WHERE LOWER(s.Patient.patientData.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<SoldBill> findSoldBillsByPatientNameContainingIgnoreCase(@Param("name") String name);


    @Query("SELECT s FROM SoldBill s WHERE s.Patient.id = :id and s.isArchived = false")
    List<SoldBill> getAllPatientSlidBillByPatientId(@Param("id") Long id);


    @Query("SELECT s FROM SoldBill s WHERE s.Patient.id = :patientId AND s.isArchived = true AND s.dateOfPayment = :date")
    List<SoldBill> getAllPatientSoldBillByPatientIdAndIsArchivedIsTrueAndDateOfPayment(Long patientId, LocalDate date);


    // TODO: 3/20/2024 this method error in start date and end date
    @Query("SELECT sb FROM SoldBill sb WHERE sb.isArchived = false " +
            "AND (:startDate IS NULL OR sb.dateOfPayment BETWEEN :startDate AND :endDate) " +
            "ORDER BY LOWER(sb.Patient.patientData.fullName) ASC, sb.createAt DESC")
    Page<SoldBill> getAllSoldBillsSortedByNameIgnoreCaseAndCreatedAtDesc(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);


    @Query("SELECT SUM(sb.billTotalPrice) FROM SoldBill sb WHERE sb.isArchived = false")
    BigDecimal countAllTotalPricesForAllSolidBillAndIsArchiveFalse();


}
