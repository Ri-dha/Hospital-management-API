package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StoreHoseDao {

    StoreHose createStoreHoseOrder(StoreHose storeHose);

    Optional<StoreHose> findStoreHoseById(Long storeHoseId);

    Page<StoreHose> getAllStoreHose(Pageable pageable);

    void updateStoreHose(StoreHose storeHose);

}
