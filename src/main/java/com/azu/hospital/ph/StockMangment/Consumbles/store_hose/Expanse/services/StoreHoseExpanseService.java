package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.services;


import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao.StoreHoseExpanseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dto.StoreHoseExpanseDto;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dto.StoreHoseExpanseDtoMapper;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dao.MainLabTubeStoreDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseExpanseService {


    private final StoreHoseExpanseDao storeHoseExpanseDao;

    private final MainLabTubeStoreDao mainLabTubeStoreDao;

    private final StoreHoseExpanseDtoMapper mapper;


    @Autowired
    public StoreHoseExpanseService(
            @Qualifier("storeHoseExpanseJpa") StoreHoseExpanseDao storeHoseExpanseDao,
            @Qualifier("MainLabTubeStoreJpa") MainLabTubeStoreDao mainLabTubeStoreDao,
            StoreHoseExpanseDtoMapper mapper
    ) {
        this.storeHoseExpanseDao = storeHoseExpanseDao;
        this.mainLabTubeStoreDao = mainLabTubeStoreDao;
        this.mapper = mapper;
    }



}
