package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;

import java.util.Optional;

public interface StoreHoseExpanseDao {
    StoreHoseExpanse createNewStoreHoseExpanse(StoreHoseExpanse storeHoseExpanse);

    Optional<StoreHoseExpanse> findById(Long id);

    Optional<StoreHoseExpanse> getByItemId(Long itemId);

    void updateStoreHoseExpanse(StoreHoseExpanse storeHoseExpanse);


}
