package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("storeHoseRepo")
public class StoreHoseDataJpa implements StoreHoseDao{

    private final StoreHoseRepository storeHoseRepository;

    public StoreHoseDataJpa(StoreHoseRepository storeHoseRepository) {
        this.storeHoseRepository = storeHoseRepository;
    }

    @Override
    public StoreHose createStoreHoseOrder(StoreHose storeHose) {
        return storeHoseRepository.save(storeHose);
    }

    @Override
    public Optional<StoreHose> findStoreHoseById(Long storeHoseId) {
        return storeHoseRepository.findById(storeHoseId);
    }

    @Override
    public Page<StoreHose> getAllStoreHose(Pageable pageable) {
        return null;
    }

    @Override
    public void updateStoreHose(StoreHose storeHose) {
       storeHoseRepository.save(storeHose);
    }
}
