package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreHoseExpanseRepository extends JpaRepository<StoreHoseExpanse , Long> {


    Optional<StoreHoseExpanse> findByMainLabTubeStoreId(Long itemId);
}
