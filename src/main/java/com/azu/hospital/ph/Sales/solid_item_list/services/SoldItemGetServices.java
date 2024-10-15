package com.azu.hospital.ph.Sales.solid_item_list.services;

import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemDao;
import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDto;
import com.azu.hospital.ph.Sales.solid_item_list.dto.SoldItemsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldItemGetServices {

    private final SoldItemDao soldItemDao;
    private final SoldItemsDtoMapper dtoMapper;

    @Autowired
    public SoldItemGetServices(@Qualifier("SoldItemJpa") SoldItemDao soldItemDao, SoldItemsDtoMapper dtoMapper) {
        this.soldItemDao = soldItemDao;
        this.dtoMapper = dtoMapper;
    }


    public SoldItemsDto getItemById(Long id){
        return soldItemDao.getItemFormListById(id)
                .map(dtoMapper)
                .orElse(null);
    }


    public Page<SoldItemsDto> getAllItems(Pageable pageable){
        List<SoldItemsDto> soldItems = soldItemDao.getAllItemLists(pageable)
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>(soldItems, pageable, pageable.getPageNumber());
    }

    public List<SoldItemsDto> getAllItemsByListId(Long billId){
        return soldItemDao.getAllItemListByBillId(billId)
                .stream()
                .map(dtoMapper)
                .toList();
    }


}
