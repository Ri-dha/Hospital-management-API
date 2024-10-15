package com.azu.hospital.ph.StockMangment.entringItemList.services;

import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MathService {

    private final StockBillDao stockBillDao;

    public MathService(StockBillDao stockBillDao) {
        this.stockBillDao = stockBillDao;
    }


    public BigDecimal calculateAllDebts(@RequestParam Integer month, @RequestParam Integer year,@RequestParam String type){
        List<StockBill> stockBillList = stockBillDao.findAllBillsByMonthAndYear(month, year, type);

        BigDecimal totalDebts = BigDecimal.ZERO;
        for (StockBill bill: stockBillList){
            totalDebts = totalDebts.add(bill.getBillTotalDebts());
        }
        return totalDebts;
    }

    public BigDecimal calculateAllPurchase(@RequestParam Integer month, @RequestParam Integer year, String type){
        List<StockBill> stockBillList = stockBillDao.findAllBillsByMonthAndYear(month, year, type);

        BigDecimal totalPurchase = BigDecimal.ZERO;
        for (StockBill bill: stockBillList){
            totalPurchase = totalPurchase.add(bill.getBillTotalAfterDiscount());
        }
        return totalPurchase;
    }

    public BigDecimal calculateTotalPurchasesNetOfDebts(@RequestParam Integer month, @RequestParam Integer year, String type) {
        BigDecimal totalDebts = calculateAllDebts(month, year, type);
        BigDecimal totalPurchase = calculateAllPurchase(month, year, type);
        return totalPurchase.subtract(totalDebts);
    }





    // TODO: 8/1/2023 calculate total soled bills, items , drugs and consumable
    // TODO: 8/1/2023 most company buying
    // TODO: 8/1/2023 most store buying
    // TODO: 8/1/2023 total debts / store
    // TODO: 8/1/2023 total purchase / store / month
    // TODO: 8/1/2023 total purchase / company / month
    // TODO: 8/1/2023 notifications for debts/ months.
}
