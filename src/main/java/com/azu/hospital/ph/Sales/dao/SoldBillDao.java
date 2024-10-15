package com.azu.hospital.ph.Sales.dao;

import com.azu.hospital.ph.Sales.entity.SoldBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SoldBillDao {

    void createNewSoldBill(SoldBill request);

    void updateExistSoldBill(SoldBill update);

    Page<SoldBill> getAllSoldBillsSortedByNameIgnoreCaseAndCreatedAtDesc(LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<SoldBill> findSoldBillByPatient(String name);

    Optional<SoldBill> getBillById(Long billId);

    List<Object[]> findMostSoldItems(Pageable pageable);


    List<SoldBill> getAllPatientSlidBillByPatientId(Long id);

    List<SoldBill> getAllPatientSoldBillByPatientIdAndIsArchivedIsTrueAndDateOfPayment(Long patientId, LocalDate date);

    BigDecimal countAllTotalPricesForAllSolidBillAndIsArchiveFalse();
}
