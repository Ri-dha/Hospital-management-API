package com.azu.hospital.ph.StockMangment.StockBillTracker.dao;

import com.azu.hospital.ph.StockMangment.StockBillTracker.entity.StockBillTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StockBillTrackerJpa")
public class StockBillTrackerDataAccess implements StockBillTrackerDao {
    private final StockBillTrackerRepository stockBillTrackerRepository;

    @Autowired
    public StockBillTrackerDataAccess(@Qualifier("stockBillTrackerRepository") StockBillTrackerRepository stockBillTrackerRepository) {
        this.stockBillTrackerRepository = stockBillTrackerRepository;
    }


    @Override
    public void createStockBillTracker(StockBillTracker tracker) {
        stockBillTrackerRepository.save(tracker);
    }

    @Override
    public void updateStockBillTracker(StockBillTracker tracker) {
        stockBillTrackerRepository.save(tracker);
    }

    @Override
    public void deleteStockBillTracker(Long id) {
        stockBillTrackerRepository.deleteById(id);
    }

    @Override
    public List<StockBillTracker> getAllStockBillTrackers() {
        return stockBillTrackerRepository.findAll();
    }

    @Override
    public List<StockBillTracker> getStockBillTrackerByBillId(Long billId) {
        return stockBillTrackerRepository.getStockBillTrackerByBillId(billId);
    }
}
