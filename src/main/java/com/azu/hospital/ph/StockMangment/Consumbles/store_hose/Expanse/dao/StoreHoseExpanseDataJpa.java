package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("storeHoseExpanseJpa")
public class StoreHoseExpanseDataJpa implements StoreHoseExpanseDao{

    private final StoreHoseExpanseRepository storeHoseExpanseRepository;

    @Autowired
    public StoreHoseExpanseDataJpa(
            StoreHoseExpanseRepository storeHoseExpanseRepository
    ) {
        this.storeHoseExpanseRepository = storeHoseExpanseRepository;
    }

    @Override
    public StoreHoseExpanse createNewStoreHoseExpanse(StoreHoseExpanse storeHoseExpanse) {
        return storeHoseExpanseRepository.save(storeHoseExpanse);
    }

    @Override
    public Optional<StoreHoseExpanse> findById(Long id) {
        return storeHoseExpanseRepository.findById(id);
    }

    @Override
    public Optional<StoreHoseExpanse> getByItemId(Long itemId) {
        return storeHoseExpanseRepository.findByMainLabTubeStoreId(itemId);
    }

    @Override
    public void updateStoreHoseExpanse(StoreHoseExpanse storeHoseExpanse) {
       storeHoseExpanseRepository.save(storeHoseExpanse);
    }
}
