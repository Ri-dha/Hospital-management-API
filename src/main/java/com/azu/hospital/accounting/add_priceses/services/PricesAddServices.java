package com.azu.hospital.accounting.add_priceses.services;

import com.azu.hospital.accounting.add_priceses.dao.PricesDao;
import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.accounting.add_priceses.request.PricesBaseRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.BillState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PricesAddServices {

    private final PricesDao dao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public PricesAddServices(@Qualifier("PricesJpa") PricesDao dao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void addPrices(PricesBaseRequest request) {

        Prices newPrices = new Prices();
        newPrices.setItemName(request.itemName());
        newPrices.setFinalPrice(request.finalPrice());
        newPrices.setType(request.type());
        newPrices.setNote(request.note());
        newPrices.setBillState(BillState.UNPAID);
        dao.addItemPrices(newPrices);
    }


    public void updatePrices(Long id, PricesBaseRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Prices existingPrices = dao.getItemPriceById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        boolean changes = false;
        if (request.itemName() != null){
            existingPrices.setItemName(request.itemName());
            changes = true;
        } if (request.finalPrice() != null){
            existingPrices.setFinalPrice(request.finalPrice());
            changes = true;
        } if (request.type() != null){
            existingPrices.setType(request.type());
            changes = true;
        } if (request.note() != null){
            existingPrices.setNote(request.note());
            changes = true;
        } if (!changes){
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }

        dao.updateItemPrices(existingPrices);
    }



    public void DeleteItemBiId(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Prices prices = dao.getItemPriceById(id)
                .orElseThrow(
                ()-> new ResourceNotFoundException(
                       messages.getString("resourceNotFound")
                )
        );

        dao.deleteItemPrices(prices.getId());
    }

}
