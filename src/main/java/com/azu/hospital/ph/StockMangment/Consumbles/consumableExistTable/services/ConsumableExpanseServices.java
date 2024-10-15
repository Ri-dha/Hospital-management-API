package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao.ConsumableExpanseTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dto.ConsumableExpanseDtMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dto.ConsumableExpanseDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ConsumableExpanseServices {

    private final ConsumableExpanseTableDao dao;
    private final ConsumableExpanseDtMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ConsumableExpanseServices(@Qualifier("ConsumableExpanseTableJpa") ConsumableExpanseTableDao dao,
                                     ConsumableExpanseDtMapper dtoMapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }


    public void updateConsumableExpanse(Long id, Integer quantity, String place){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsumableExpanseTable expanseTable = dao.getConsumablesById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + id
                        )
                );

        boolean changes = false;

        if (quantity != null){
            expanseTable.setQuantity(quantity);
            changes = true;
        }if (place != null){
            expanseTable.setConsumablePlace(place);
            changes = true;
        }if (!changes){
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
        dao.updateConsumables(expanseTable);
    }



    public ConsumableExpanseDto getExpanseById(Long id){
        return dao.getConsumablesById(id)
                .stream()
                .map(dtoMapper)
                .findFirst()
                .orElse(null);
    }

    public Page<ConsumableExpanseDto> getAllConsumables(String name, String barcode, Pageable pageable){
        List<ConsumableExpanseDto> dtolist = dao.getAllConsumablesAndSort(name, barcode, pageable)
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>(dtolist, pageable, pageable.getPageSize());
    }

}