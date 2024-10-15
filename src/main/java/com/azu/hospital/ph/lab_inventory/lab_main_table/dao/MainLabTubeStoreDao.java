package com.azu.hospital.ph.lab_inventory.lab_main_table.dao;

import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MainLabTubeStoreDao {


    MainLabTubeStore createMainLapTubeStore(MainLabTubeStore request);

    void updateMainLapTubeStore(MainLabTubeStore update);

    void deleteMainLapTubeStore(Long id);

    Optional<MainLabTubeStore> findMainLapTubeStoreById(Long id);

    Page<MainLabTubeStore> getAllMainLapTubeStore(Pageable pageable);

    long countMainlabtube();
}
