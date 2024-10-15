package com.azu.hospital.ph.lab_inventory.lab_main_table.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.exceptions.validators.DateValidations;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.dao.StoreHoseExpanseDao;
import com.azu.hospital.ph.StockMangment.Consumbles.store_hose.Expanse.entity.StoreHoseExpanse;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dao.MainLabTubeStoreDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.ph.lab_inventory.lab_main_table.request.MainLabTubeStoreRegistrationRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainLabTubeStoreAddService {

    private final MainLabTubeStoreDao mainLabTubeStoreDao;
    private final StockBillDao stockBillDao;
    private final ImageService imageService;
    private final DateValidations dateValidations;

    private final StoreHoseExpanseDao storeHoseExpanseDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public MainLabTubeStoreAddService(
            @Qualifier("MainLabTubeStoreJpa") MainLabTubeStoreDao mainLabTubeStoreDao,
            StockBillDao stockBillDao,
            ImageService imageService,
            DateValidations dateValidations,
            @Qualifier("storeHoseExpanseJpa") StoreHoseExpanseDao storeHoseExpanseDao, ExceptionsMessageReturn messageReturn
    ) {
        this.mainLabTubeStoreDao = mainLabTubeStoreDao;
        this.stockBillDao = stockBillDao;
        this.imageService = imageService;
        this.dateValidations = dateValidations;
        this.storeHoseExpanseDao = storeHoseExpanseDao;
        this.messageReturn = messageReturn;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void addNewLabTube(
            @RequestParam(name = "billId")Long billId,
            MainLabTubeStoreRegistrationRequest request
    ) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        StockBill stockBill = stockBillDao.getBillById(billId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("stockBillNotFound")+" "+billId
                        )
                );

        MainLabTubeStore store = new MainLabTubeStore
                .Builder()
                .withItemTubeName(request.tubeName())
                .withItemTubeCompany(request.tubeCompany())
                .withItemTubeQuantity(request.quantity())
                .withItemBuyingPrice(request.itemBuyingPrice())
                .withItemTubeBarcode(request.barcode())
                .withItemTubeDescription(request.descriptions())
                .withItemTubeProDate(request.proDate())
                .withItemTubeExpDate(request.expDate())
                .withItemTubePlaceInStore(request.place())
                .withStorageType(request.storageType())
                .withTubeType(request.type())
                .build();
        if (request.image() != null ){
            String image = imageService.saveImage(request.image());
            store.setItemImageUrl(image);
        }
        dateValidations.changeDate(request.expDate());
        stockBill.setMainLabTubeStores(List.of(store));
        store.setBill(stockBill);
        store = mainLabTubeStoreDao.createMainLapTubeStore(store);

        StoreHoseExpanse storeHoseExpanse = new StoreHoseExpanse(0);
        storeHoseExpanse.setMainLabTubeStore(store);
        storeHoseExpanseDao.createNewStoreHoseExpanse(storeHoseExpanse);

    }
}
