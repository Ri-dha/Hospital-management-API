package com.azu.hospital.ph.StockMangment.entringItemList.dao;

import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StockBillDao {

    StockBill insertBill(StockBill stockBill);

    void updateBill(StockBill stockBill);

    void deleteBillBy(Long billId);

    Page<StockBill> getAllBills(Pageable pageable);

    Optional<StockBill> getBillById(Long billId);

    Page<StockBill> findBillsBy(String supplierDetails, String pharmacy, String billEntryDate, BigDecimal billNumber, StockBillState state, Pageable pageable);

    boolean findBillBy(String supplierDetails, BigDecimal billNumber, BigDecimal billTotalPrice);

    List<StockBill> findAllBillsByMonthAndYear(Integer month, Integer year, String type);

    Page<StockBill> getAllByItemContaining(String type, Pageable pageable);


    void deleteDrugBillContain(Long itemId, Long billId);

    void deleteConsumableBillContain(Long itemId, Long billId);

    void deleteItemBillContain(Long itemId, Long billId);


}
