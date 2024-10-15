package com.azu.hospital.ph.StockMangment.Consumbles.dao;


import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConsumableDao {

    Page<Consumables> selectAllConsumables(String consumableName,
                                           String barcode, Pageable pageable);

    Optional<Consumables> selectConsumableById(Long consumableId);

    Optional<Consumables> findConsumableById(Long consumableId);

    Consumables insertConsumable(Consumables consumables);

    void updateConsumable(Consumables update);

    void deleteConsumableById(Long consumableId);

    boolean consumableExistById(Long consumableId);

//    boolean consumableExistByBarcode(String barcode);

    List<Consumables> getConsumablesByBillId(Long consumableId);


    Optional<Consumables> findConsumablesByBarcode(String barcode);

    Optional<Consumables> findConsumablesByConsumableNameContainingIgnoreCase(String consumableName);
}
