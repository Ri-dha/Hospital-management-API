package com.azu.hospital.ph.StockMangment.entringItemList.services;


import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.request.StockRegistrationRequest;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AddBillServices {

    private final StockBillDao stockBillDao;


    public AddBillServices(@Qualifier("BillJpa")
                           StockBillDao stockBillDao
    ) {
        this.stockBillDao = stockBillDao;
    }

    @Transactional
    public void addNewBill(StockRegistrationRequest registrationRequest) {
        String pharmacyName = registrationRequest.pharmacy();

        String supplierDetails = registrationRequest.supplierDetails();

        checkBillValidity(registrationRequest.supplierDetails(), registrationRequest.billNumber(), registrationRequest.billTotalPrice());

        StockBill stockBill = new StockBill(
                registrationRequest.pharmacy(),
                registrationRequest.supplierDetails(),
                registrationRequest.billNumber(),
                registrationRequest.billDate(),
                registrationRequest.billEntryDate(),
                registrationRequest.billTotalPrice(),
                registrationRequest.billTotalDiscount(),
                registrationRequest.billTotalDebts(),
                registrationRequest.billTotalMoneyPaid(),
                registrationRequest.billTotalAfterDiscount(),
                registrationRequest.totalRestMoney(),
                registrationRequest.billDescriptions(),
                registrationRequest.location(),
                registrationRequest.mobile()
        );

        setBillState(registrationRequest, stockBill);
         stockBillDao.insertBill(stockBill);
    }

    private static void setBillState(StockRegistrationRequest registrationRequest, StockBill stockBill) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal totalDebts = registrationRequest.billTotalDebts();
        BigDecimal totalAfterDiscount = registrationRequest.billTotalAfterDiscount();
        if (totalDebts.equals(zero)) {
            stockBill.setBillState(StockBillState.PAID);
        } else if (totalDebts.equals(totalAfterDiscount)) {
            stockBill.setBillState(StockBillState.UNPAID);
        } else if (!totalDebts.equals(totalAfterDiscount) && !totalDebts.equals(zero)) {
            stockBill.setBillState(StockBillState.PARTIALLY);
        } else {
            throw new IllegalArgumentException("bill Total Debts and bill Total price After Discount");
        }
    }

    private void checkBillValidity(String supplierDetails, BigDecimal billNumber, BigDecimal billTotalPrice){
        if (stockBillDao.findBillBy(supplierDetails, billNumber, billTotalPrice)){
            throw new DuplicateResourceException(
                    "This List Already Exist [%s]".formatted(billNumber)
            );
        }
    }
}
