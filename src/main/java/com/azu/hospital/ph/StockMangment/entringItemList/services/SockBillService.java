package com.azu.hospital.ph.StockMangment.entringItemList.services;


import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.ph.StockMangment.entringItemList.dto.StockBillDto;
import com.azu.hospital.ph.StockMangment.entringItemList.dto.StockBillDtoMapper;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Service
public class SockBillService {
    private final StockBillDao stockBillDao;
    private final StockBillDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public SockBillService(@Qualifier("BillJpa") StockBillDao stockBillDao,
                           StockBillDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn) {
        this.stockBillDao = stockBillDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    //**************************************************************Read***********************************************\\


    public StockBillDto getBillById(Long billId) {

        Locale locale = messageReturn.getMessageLocally();

        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        return stockBillDao.getBillById(billId)
                .map(dtoMapper)
                .orElseThrow(
                        () ->new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }


    public Page<StockBillDto> findByBillBY(
            String supplierDetails, String pharmacy, String billEntryDate, BigDecimal billNumber, StockBillState state, Pageable pageable
    ) {
        Page<StockBill> stockBillPage = stockBillDao.findBillsBy(supplierDetails, pharmacy, billEntryDate, billNumber, state, pageable);
        List<StockBillDto> stockBillDtoList = stockBillPage.map(dtoMapper).getContent();
        return new PageImpl<>(stockBillDtoList, pageable, stockBillPage.getTotalElements());
    }




    //**************************************************************Delete***********************************************\\
    public void deleteBillBYId(Long billId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        StockBill bill = stockBillDao.getBillById(billId).orElseThrow(
                () ->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        stockBillDao.deleteBillBy(bill.getBillId());
    }
    //**************************************************************Other***********************************************\\

    boolean checkBillValidity(String supplierDetails, BigDecimal billNumber, BigDecimal billTotalPrice){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        if (stockBillDao.findBillBy(supplierDetails, billNumber, billTotalPrice)){
           throw new DuplicateResourceException(
                    messages.getString("resourceFound")
            );
        }
        return true;
    }


    public StockBillDtoMapper getDtoMapper() {
        return this.dtoMapper;
    }




    public Page<StockBillDto> getAllBillBy(String type, Pageable pageable){
        Page<StockBill> billList = stockBillDao.getAllByItemContaining(type, pageable);
        List<StockBillDto> dtoList = billList
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>( dtoList,pageable, pageable.getPageSize());
    }
}
