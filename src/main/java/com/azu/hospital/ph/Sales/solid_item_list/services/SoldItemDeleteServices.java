package com.azu.hospital.ph.Sales.solid_item_list.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemDao;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldItemDeleteServices {

    private final SoldItemDao soldItemDao;

    @Autowired
    public SoldItemDeleteServices(SoldItemDao soldItemDao) {
        this.soldItemDao = soldItemDao;
    }

    public void deleteById(Long id){
        SoldItems items = soldItemDao.getItemFormListById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                "item not found"
                        )
                );
        soldItemDao.deleteSolidItemById(items.getId());
    }
}
