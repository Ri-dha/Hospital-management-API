package com.azu.hospital.ph.StockMangment.entringItemList.dao;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository("BillJpa")
public class StockBillJPADataAccess implements StockBillDao {
    private final StockBillRepository stockBillRepository;

    @Autowired
    public StockBillJPADataAccess(StockBillRepository stockBillRepository) {
        this.stockBillRepository = stockBillRepository;
    }

    @Override
    public StockBill insertBill(StockBill stockBill) {
        return stockBillRepository.save(stockBill);
    }

    @Override
    public void updateBill(StockBill stockBill) {
         stockBillRepository.save(stockBill);
    }


    @Override
    public void deleteBillBy(Long billId) {
         stockBillRepository.deleteById(billId);
    }

    @Override
    public Page<StockBill> getAllBills(Pageable pageable) {
        return stockBillRepository.findAll(pageable);
    }

    @Override
    public Optional<StockBill> getBillById(Long billId) {
        return stockBillRepository.findById(billId);
    }

    @Override
    public Page<StockBill> findBillsBy(String supplierDetails, String pharmacy, String billEntryDate, BigDecimal billNumber, StockBillState state, Pageable pageable) {

        return stockBillRepository.findBillsBy(supplierDetails,  pharmacy,billEntryDate, billNumber,state, pageable);
    }

    @Override
    public boolean findBillBy(String supplierDetails, BigDecimal billNumber, BigDecimal billTotalPrice) {
        return stockBillRepository.findBillBy(supplierDetails, billNumber, billTotalPrice);
    }

    @Override
    public List<StockBill> findAllBillsByMonthAndYear(Integer month, Integer year, String type) {
        return stockBillRepository.findAllByMonthAndYear(month , year, type);
    }

    @Override
    public Page<StockBill> getAllByItemContaining(String type, Pageable pageable) {
        return stockBillRepository.findAllByItemContaining(type, pageable);
    }

    @Override
    public void deleteDrugBillContain(Long itemId, Long billId) {
        stockBillRepository.deleteDrugByIdAndBillId(itemId, billId);
    }

    @Override
    public void deleteConsumableBillContain(Long itemId, Long billId) {
        stockBillRepository.deleteConsumableByIdAndBillId(itemId, billId);
    }

    @Override
    public void deleteItemBillContain(Long itemId, Long billId) {
        stockBillRepository.deleteOtherItemByIdAndBillId(itemId, billId);
    }


}
