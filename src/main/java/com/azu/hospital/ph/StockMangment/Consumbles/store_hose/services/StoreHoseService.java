package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services;

import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao.StoreHoseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto.StoreHoseDto;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto.StoreHoseDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseService {

    private final StoreHoseDao storeHoseDao;
    private final StoreHoseDtoMapper mapper;

    @Autowired
    public StoreHoseService(
            @Qualifier("storeHoseRepo") StoreHoseDao storeHoseDao,
            StoreHoseDtoMapper mapper
    ) {
        this.storeHoseDao = storeHoseDao;
        this.mapper = mapper;
    }





    public Page<StoreHoseDto> getStoreHoseOrder(Pageable pageable){
        return storeHoseDao.getAllStoreHose(pageable).map(mapper::toDto);
    }
}
