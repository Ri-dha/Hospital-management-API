package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface StoreHoseRepository extends JpaRepository<StoreHose , Long> {
}
