package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao.StoreHoseExpanseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao.StoreHoseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto.StoreHoseDto;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto.StoreHoseDtoMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.request.CreateStoreHoseRequest;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dao.MainLabTubeStoreDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.utils.enums.storeHose.StoreHoseStatusEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseLabSideService {

    private final StoreHoseDao storeHoseDao;

    private final MainLabTubeStoreDao mainLabTubeStoreDao;

    private final StoreHoseDtoMapper mapper;

    private final StoreHoseExpanseDao storeHoseExpanseDao;

    public StoreHoseLabSideService(
            @Qualifier("storeHoseRepo") StoreHoseDao storeHoseDao,
            @Qualifier("MainLabTubeStoreJpa") MainLabTubeStoreDao mainLabTubeStoreDao,
            @Qualifier("storeHoseExpanseJpa") StoreHoseExpanseDao storeHoseExpanseDao,
            StoreHoseDtoMapper mapper
    ) {
        this.storeHoseDao = storeHoseDao;
        this.mainLabTubeStoreDao = mainLabTubeStoreDao;
        this.mapper = mapper;
        this.storeHoseExpanseDao = storeHoseExpanseDao;
    }


    public StoreHoseDto createStoreHose(CreateStoreHoseRequest request){


        MainLabTubeStore mainLabTubeStore = mainLabTubeStoreDao.findMainLapTubeStoreById(request.getItemId()).orElseThrow(
                () -> new ResourceNotFoundException("Item Doesn't Exists")
        );

        StoreHoseExpanse storeHoseExpanse =
                storeHoseExpanseDao.getByItemId(mainLabTubeStore.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Item Doesn't Exists")
                );

        if (request.getCount() > (mainLabTubeStore.getItemTubeQuantity() - storeHoseExpanse.getQuantity())){
              throw new BadRequestException("Insufficient Count");
        }

        StoreHose storeHose = new StoreHose(
                request.getCount(),
                request.getNote(),
                StoreHoseStatusEnum.Waiting
        );

        storeHose.setItem(mainLabTubeStore);

        storeHose = storeHoseDao.createStoreHoseOrder(storeHose);

        return mapper.toDto(storeHose);

    }
    public void receivedStoreHoseOrder(Long storeHoseId){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Pending){
            throw new BadRequestException("Order Must Be Pending");
        }

        storeHose.setState(StoreHoseStatusEnum.Received);

        storeHoseDao.updateStoreHose(storeHose);

    }

    public void notReceivedStoreHoseOrder(Long storeHoseId){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Pending){
            throw new BadRequestException("Order Must Be Pending");
        }

        storeHose.setState(StoreHoseStatusEnum.NotReceived);

        storeHoseDao.updateStoreHose(storeHose);

    }

    public void cancelStoreHoseOrder(Long storeHoseId){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Waiting && storeHose.getState() != StoreHoseStatusEnum.Accepted){
            throw new BadRequestException("Order Must Be Waitting Or Accepted");
        }

        storeHose.setState(StoreHoseStatusEnum.Cancel);

        storeHoseDao.updateStoreHose(storeHose);

    }
}
