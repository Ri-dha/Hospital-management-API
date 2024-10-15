package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConsumableExpanseTableDao {

    void addConsumables(ConsumableExpanseTable request);

    void updateConsumables(ConsumableExpanseTable update);

    void deleteConsumablesById(Long id);

    Optional<ConsumableExpanseTable> getConsumablesById(Long id);

    List<ConsumableExpanseTable> getAllConsumablesAndSort(String name, String barcode, Pageable pageable);

    Optional<ConsumableExpanseTable> getConsumablesByBarcode(String barcode);
    List<ConsumableExpanseTable> findByMainsConsumables(Long id);
}
