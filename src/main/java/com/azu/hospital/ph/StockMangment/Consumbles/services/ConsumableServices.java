package com.azu.hospital.ph.StockMangment.Consumbles.services;


import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDto;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDtoSpecial;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDtoSpecialDtoMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.Dto.FirstConsumableMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumableServices {
    private final ConsumableDao consumableDao;
    private final FirstConsumableMapper consumableDtoMapper;

    private final ConsumableDtoSpecialDtoMapper mapper;




    @Autowired
    public ConsumableServices(@Qualifier("consumableJpa")
                              ConsumableDao consumableDao,
                              FirstConsumableMapper consumableDtoMapper,
                              ConsumableDtoSpecialDtoMapper mapper) {
        this.consumableDao = consumableDao;
        this.consumableDtoMapper = consumableDtoMapper;
        this.mapper = mapper;
    }

    public Page<ConsumableDto> findByDrugSortedBOrderBy(
            String consumableName,
            String barcode,
            Pageable pageable
    ) {

        return consumableDao.selectAllConsumables(consumableName, barcode, pageable)
                .map(consumableDtoMapper);
    }
    public Page<ConsumableDtoSpecial> findConsumablesBySortedBOrderBy(
            String consumableName,
            String barcode,
            Pageable pageable
    ) {

        Page<Consumables> consumableDtoPage = consumableDao.selectAllConsumables(consumableName, barcode, pageable);
        List<ConsumableDtoSpecial> dtoSpecials = consumableDtoPage
                .stream().map(mapper)
                .toList();
        return new PageImpl<>(dtoSpecials, pageable, consumableDtoPage.getTotalElements());
    }

    public ConsumableDto getConsumableById(Long consumableId){
        return consumableDao.selectConsumableById(consumableId)
                .stream()
                .map(consumableDtoMapper)
                .findFirst()
                .orElse(null);
    }

    public ConsumableDto getConsumableByBarcode(String barcode){
        return consumableDao.findConsumablesByBarcode(barcode)
                .stream()
                .map(consumableDtoMapper)
                .findFirst()
                .orElse(null);
    }



    public void deleteConsumableBYId(Long consumableId){
        checkIfConsumableExistsById(consumableId);
        consumableDao.deleteConsumableById(consumableId);
    }

    public void checkIfConsumableExistsById( Long consumableId){
        if (!consumableDao.consumableExistById(consumableId)){
            throw new ResourceNotFoundException(
                    "Consumable with exists Id[%s] Not Found".formatted(consumableId)
            );
        }
    }

}
