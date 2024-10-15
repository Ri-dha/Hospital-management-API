package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("ConsumableExpanseTableJpa")
public class ConsumableExpanseTableJpaDataAccess implements ConsumableExpanseTableDao {

    private final ConsumableExpanseTableRepository repository;
    @Autowired
    public ConsumableExpanseTableJpaDataAccess(ConsumableExpanseTableRepository repository) {
        this.repository = repository;
    }
    @Override
    public void addConsumables(ConsumableExpanseTable request) {
        repository.save(request);
    }

    @Override
    public void updateConsumables(ConsumableExpanseTable update) {
        repository.save(update);
    }

    @Override
    public void deleteConsumablesById(Long id) {
   repository.deleteById(id);
    }

    @Override
    public Optional<ConsumableExpanseTable> getConsumablesById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ConsumableExpanseTable> getAllConsumablesAndSort(String name, String barcode, Pageable pageable) {
        return repository.findAllConsumablesAndSort(name, barcode, pageable);
    }

    @Override
    public Optional<ConsumableExpanseTable> getConsumablesByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public List<ConsumableExpanseTable> findByMainsConsumables(Long id) {
        return repository.findByMainsConsumables(id);
    }
}
