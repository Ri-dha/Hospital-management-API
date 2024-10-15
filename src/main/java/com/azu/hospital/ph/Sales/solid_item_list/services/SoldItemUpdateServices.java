package com.azu.hospital.ph.Sales.solid_item_list.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.solid_item_list.request.SoldItemsUpdateRequest;
import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemDao;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldItemUpdateServices {

    private final SoldItemDao soldItemDao;

    @Autowired
    public SoldItemUpdateServices(@Qualifier("SoldItemJpa") SoldItemDao soldItemDao) {
        this.soldItemDao = soldItemDao;
    }

    public void updateItemList(Long id, SoldItemsUpdateRequest updateRequest){
        SoldItems existItems = soldItemDao.getItemFormListById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                "Item with id " + id + " does not exist"
                        )
                );

        boolean changes = false;

        if (updateRequest.drugTradeName() != null){
            existItems.setItemName(updateRequest.drugTradeName());
            changes = true;
        }if (updateRequest.quantity() != null){
            existItems.setQuantity(updateRequest.quantity());
            changes = true;
        }if (!changes){
            throw new ResourceNotFoundException(
                    "There is no changes"
            );
        }
        soldItemDao.updateExistItem(List.of(existItems));
    }
}
