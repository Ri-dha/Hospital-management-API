package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao.StoreHoseExpanseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dao.StoreHoseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.entity.StoreHose;
import com.azu.hospital.utils.enums.storeHose.StoreHoseStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StoreHoseInventorySideService {

    private final StoreHoseDao storeHoseDao;

    private final StoreHoseExpanseDao storeHoseExpanseDao;


    public StoreHoseInventorySideService(
            @Qualifier("storeHoseRepo") StoreHoseDao storeHoseDao,
            @Qualifier("storeHoseExpanseJpa") StoreHoseExpanseDao storeHoseExpanseDao
    ) {
        this.storeHoseDao = storeHoseDao;
        this.storeHoseExpanseDao = storeHoseExpanseDao;
    }


    public void acceptStoreHoseOrder(Long storeHoseId , String receivedNote){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Waiting){
            throw new BadRequestException("Order Must Be Waitting");
        }

        storeHose.setReceivedNote(receivedNote);
        storeHose.setState(StoreHoseStatusEnum.Accepted);

        storeHoseDao.updateStoreHose(storeHose);

    }


    public void rejectStoreHoseOrder(Long storeHoseId , String receivedNote){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Waiting){
            throw new BadRequestException("Order Must Be Waitting");
        }

        storeHose.setReceivedNote(receivedNote);
        storeHose.setState(StoreHoseStatusEnum.Rejected);

        storeHoseDao.updateStoreHose(storeHose);
    }

    public void preparingStoreHoseOrder(Long storeHoseId){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Accepted){
            throw new BadRequestException("Order Must Be Accepted");
        }

        storeHose.setState(StoreHoseStatusEnum.Prepare);

        storeHoseDao.updateStoreHose(storeHose);

    }

    @Transactional
    public void pendingStoreHoseOrder(Long storeHoseId){
        StoreHose storeHose = storeHoseDao.findStoreHoseById(storeHoseId).orElseThrow(
                ()->new ResourceNotFoundException("Order Doesn't Found")
        );

        if (storeHose.getState() != StoreHoseStatusEnum.Prepare){
            throw new BadRequestException("Order Must Be Prepare");
        }

        StoreHoseExpanse storeHoseExpanse = storeHoseExpanseDao.getByItemId(storeHose.getItem().getId()).orElseThrow(
                () -> new ResourceNotFoundException("item Doesn't Exists")
        );

        storeHoseExpanse.setQuantity(storeHoseExpanse.getQuantity() + storeHose.getCount());

        storeHoseExpanseDao.updateStoreHoseExpanse(storeHoseExpanse);

        storeHose.setState(StoreHoseStatusEnum.Pending);

        storeHoseDao.updateStoreHose(storeHose);

    }








}
