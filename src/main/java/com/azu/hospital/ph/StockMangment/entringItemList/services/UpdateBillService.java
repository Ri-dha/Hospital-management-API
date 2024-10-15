package com.azu.hospital.ph.StockMangment.entringItemList.services;

import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.request.StockUpdateRequest;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;


@Service
public class UpdateBillService {
    private final StockBillDao stockBillDao;



    @Autowired
    public UpdateBillService(@Qualifier("BillJpa")
                             StockBillDao stockBillDao) {
        this.stockBillDao = stockBillDao;


    }


    public void stockBillUpdate(Long billId, StockUpdateRequest update){
        StockBill stockBill = stockBillDao.getBillById(billId).orElseThrow(
                () ->new ResourceNotFoundException(
                        "List Dose not Exist"
                )
        );

        boolean changes = false;




        if (update.pharmacyName() !=null && !update.pharmacyName().equals(stockBill.getPharmacyName())){
            stockBill.setPharmacyName(update.pharmacyName());
            changes = true;
        }if (update.supplierDetails() !=null && !update.supplierDetails().equals(stockBill.getSupplierDetails())){
            stockBill.setSupplierDetails(update.supplierDetails());
            changes = true;
        }if (update.billNumber() !=null && !update.billNumber().equals(stockBill.getBillNumber())){
            stockBill.setBillNumber(update.billNumber());
            changes = true;
        }if (update.billDate() !=null && !update.billDate().equals(stockBill.getBillDate())){
            stockBill.setBillDate(update.billDate());
            changes = true;
        }if (update.billEntryDate() !=null && !update.billEntryDate().equals(stockBill.getBillEntryDate())){
            stockBill.setBillEntryDate(update.billEntryDate());
            changes = true;
        }if (update.billTotalMoneyPaid() !=null && !update.billTotalMoneyPaid().equals(stockBill.getBillTotalMoneyPaid())){
            stockBill.setBillTotalMoneyPaid(update.billTotalMoneyPaid());
            changes = true;
        }if (update.billTotalPrice() !=null && !update.billTotalPrice().equals(stockBill.getBillTotalPrice())){
            stockBill.setBillTotalPrice(update.billTotalPrice());
            changes = true;
        }if (update.billTotalDiscount() !=null && !update.billTotalDiscount().equals(stockBill.getBillTotalDiscount())){
            stockBill.setBillTotalDiscount(update.billTotalDiscount());
            changes = true;
        }if (update.billTotalDebts() !=null && !update.billTotalDebts().equals(stockBill.getBillTotalDebts())){
            stockBill.setBillTotalDebts(update.billTotalDebts());
            if (Objects.equals(stockBill.getBillTotalDebts(), BigDecimal.ZERO)){
                stockBill.setBillState(StockBillState.PAID);
            }
            changes = true;
        }if (update.totalRestMoney() !=null && !update.totalRestMoney().equals(stockBill.getTotalRestMoney())){
            stockBill.setTotalRestMoney(update.totalRestMoney());
            changes = true;
        }if (update.billTotalAfterDiscount() !=null && !update.billTotalAfterDiscount().equals(stockBill.getBillTotalAfterDiscount())){
            stockBill.setBillTotalAfterDiscount(update.billTotalAfterDiscount());
            changes = true;
        }if (update.billDescriptions() !=null && !update.billDescriptions().equals(stockBill.getBillDescriptions())){
            stockBill.setBillDescriptions(update.billDescriptions());
            changes = true;
        }if (!changes) {
            throw new RequestValidationException("No Data changes found");
        }
        stockBillDao.updateBill(stockBill);
        stockBill.setBillId(billId);

    }



    // TODO: 1/19/2024 if user update the debts ==> totalRestMoney = bill.getTotalRestMoney - debts 
    // TODO: 1/19/2024 more ides


}
