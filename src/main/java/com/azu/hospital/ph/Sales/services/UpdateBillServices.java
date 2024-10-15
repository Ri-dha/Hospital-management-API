package com.azu.hospital.ph.Sales.services;


import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.Request.SoldBillUpdateRequest;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;


@Service
public class UpdateBillServices {

    private final SoldBillDao soldBillDao;
    private final ExceptionsMessageReturn messageReturn;



    public UpdateBillServices(@Qualifier("soldJpa") SoldBillDao soldBillDao, ExceptionsMessageReturn messageReturn) {
        this.soldBillDao = soldBillDao;
        this.messageReturn = messageReturn;
    }


    public void solidBillUpdate(
            @NotNull(message = "billId  Should Not be null") @RequestParam Long billId,
            @NotNull(message = "Update Body Should Not be null") @RequestBody SoldBillUpdateRequest update) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        SoldBill soldBill = soldBillDao.getBillById(billId).orElseThrow(

                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )

        );
        boolean changes = false;
        if (update.getBillTotalPrice() != null) {
            soldBill.setBillTotalPrice(update.getBillTotalPrice());
            changes = true;
        }
       if (!changes) {
            throw new RequestValidationException(
                    messages.getString("noChanges")
            );
        }
        soldBillDao.updateExistSoldBill(soldBill);

    }




}
