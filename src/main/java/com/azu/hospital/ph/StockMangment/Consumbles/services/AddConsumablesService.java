package com.azu.hospital.ph.StockMangment.Consumbles.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.ConsumableRegistrationRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao.ConsumableExpanseTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Service
public class AddConsumablesService {
    private final ConsumableDao consumableDao;
    private final StockBillDao stockBillDao;
    private final ImageService imageService;
    private final ConsumableExpanseTableDao expanseTableDao;


    public AddConsumablesService(@Qualifier("consumableJpa")
                                 ConsumableDao consumableDao,
                                 StockBillDao stockBillDao, ImageService imageService,
                                 @Qualifier("ConsumableExpanseTableJpa") ConsumableExpanseTableDao expanseTableDao) {
        this.consumableDao = consumableDao;
        this.stockBillDao = stockBillDao;
        this.imageService = imageService;
        this.expanseTableDao = expanseTableDao;
    }

    public void registerConsumable(ConsumableRegistrationRequest request,
                                   @RequestParam(name = "billId") Long billId) throws IOException {


        StockBill stockBill = stockBillDao.getBillById(billId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "List With Id [%s] Dose Not Exist".formatted(billId)
                        )
                );

        String imageUrl = null;
        if (request.consumableImage() != null && !request.consumableImage().isEmpty()) {
            imageUrl = imageService.saveImage(request.consumableImage());
        }

        Consumables consumables = new Consumables(
                    request.consumableName(),
                    request.consumableCompany(),
                    request.quantity(),
                    request.price(),
                    request.consumableSellingPrice(),
                    request.barcode(),
                    request.expDate(),
                    request.proDate(),
                    request.typeOfCurrency(),
                    request.exchange(),
                    request.description(),
                    request.bounce(),
                    request.consumablePharmacyPlace(),
                    imageUrl
            );

        ConsumableExpanseTable expanseTable = new ConsumableExpanseTable();
        consumables.setBill(stockBill);
        expanseTable.setMainsConsumables(consumables);
        consumables.setExistsTable(expanseTable);
        stockBill.setConsumables(List.of(consumables));
        expanseTableDao.addConsumables(expanseTable);
        consumableDao.insertConsumable(consumables);

    }
}
