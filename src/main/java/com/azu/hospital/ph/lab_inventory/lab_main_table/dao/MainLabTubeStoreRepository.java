package com.azu.hospital.ph.lab_inventory.lab_main_table.dao;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface MainLabTubeStoreRepository extends JpaRepository<MainLabTubeStore, Long> {

    @Query("SELECT COUNT(u) FROM MainLabTubeStore u")
    long countMainLabTubeStore();
}
