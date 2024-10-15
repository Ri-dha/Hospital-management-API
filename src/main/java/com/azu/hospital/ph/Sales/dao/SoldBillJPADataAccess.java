package com.azu.hospital.ph.Sales.dao;

import com.azu.hospital.ph.Sales.customer.entity.Customer;

import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.Sales.customer.dao.CustomerRepository;
import com.azu.hospital.ph.Sales.repo.SoldBillRepository;
import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("soldJpa")
@Qualifier("soldJpa")
public class SoldBillJPADataAccess implements SoldBillDao{

    private final SoldBillRepository soldBillRepository;
    private final SoldItemsRepository soldItemsRepository;

    public SoldBillJPADataAccess(SoldBillRepository soldBillRepository,
                                 SoldItemsRepository soldItemsRepository) {
        this.soldBillRepository = soldBillRepository;
        this.soldItemsRepository = soldItemsRepository;
    }

    @Override
    public void createNewSoldBill(SoldBill request) {
         soldBillRepository.save(request);
    }

    @Override
    public void updateExistSoldBill(SoldBill update) {
        soldBillRepository.save(update);
    }

    @Override
    public Page<SoldBill> getAllSoldBillsSortedByNameIgnoreCaseAndCreatedAtDesc(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return soldBillRepository.getAllSoldBillsSortedByNameIgnoreCaseAndCreatedAtDesc( startDate,  endDate,  pageable);
    }

    @Override
    public List<SoldBill> findSoldBillByPatient(String name) {
        return soldBillRepository.findSoldBillsByPatientNameContainingIgnoreCase(name);
    }

    @Override
    public Optional<SoldBill> getBillById(Long billId) {
        return soldBillRepository.findById(billId);
    }


    @Override
    public List<Object[]> findMostSoldItems(Pageable pageable) {
        return soldItemsRepository.findMostSoldItems(pageable);
    }

    @Override
    public List<SoldBill> getAllPatientSlidBillByPatientId(Long id) {
        return soldBillRepository.getAllPatientSlidBillByPatientId(id);
    }

    @Override
    public List<SoldBill> getAllPatientSoldBillByPatientIdAndIsArchivedIsTrueAndDateOfPayment(Long patientId, LocalDate date) {
        return soldBillRepository.getAllPatientSoldBillByPatientIdAndIsArchivedIsTrueAndDateOfPayment(patientId, date);
    }

    @Override
    public BigDecimal countAllTotalPricesForAllSolidBillAndIsArchiveFalse() {
        return soldBillRepository.countAllTotalPricesForAllSolidBillAndIsArchiveFalse();
    }


}
