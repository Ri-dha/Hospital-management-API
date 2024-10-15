package com.azu.hospital.ph.lab_inventory.lab_main_table.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.exceptions.validators.DateValidations;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dao.MainLabTubeStoreDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import com.azu.hospital.ph.lab_inventory.lab_main_table.request.MainLabTubeStoreRegistrationRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainLabTubeStoreUpdateService {

    private final MainLabTubeStoreDao mainLabTubeStoreDao;
    private final ImageService imageService;
    private final DateValidations dateValidations;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public MainLabTubeStoreUpdateService(
            @Qualifier("MainLabTubeStoreJpa")MainLabTubeStoreDao mainLabTubeStoreDao,
            ImageService imageService,
            DateValidations dateValidations, ExceptionsMessageReturn messageReturn) {
        this.mainLabTubeStoreDao = mainLabTubeStoreDao;
        this.imageService = imageService;
        this.dateValidations = dateValidations;
        this.messageReturn = messageReturn;
    }

    public void updateMainLabTubeStore(@RequestParam(name = "id") Long id, MainLabTubeStoreRegistrationRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        MainLabTubeStore store = mainLabTubeStoreDao.findMainLapTubeStoreById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("labTubeNotFound") + " " + id
                        )
                );

        boolean changes = false;

        if (request.tubeName() != null){
            store.setItemTubeName(request.tubeName());
            changes = true;
        }
        if (request.tubeCompany() != null){
            store.setItemTubeCompany(request.tubeCompany());
            changes = true;
        }
        if (request.quantity() != null){
            store.setItemBuyingPrice(request.itemBuyingPrice());
            changes = true;
        }
        if (request.barcode() != null){
            store.setItemTubeBarcode(request.barcode());
            changes = true;
        }
        if (request.descriptions() != null){
            store.setItemTubeDescription(request.descriptions());
            changes = true;
        }
        if (request.proDate() != null){
            store.setItemTubeProDate(request.proDate());
            changes = true;
        }
        if (request.expDate() != null){
            dateValidations.changeDate(request.expDate());
            store.setItemTubeExpDate(request.expDate());
            changes = true;
        }
        if (request.place() != null){
            store.setItemTubePlaceInStore(request.place());
            changes = true;
        }
        if (request.storageType() != null){
            store.setStorageType(request.storageType());
            changes = true;
        }
        if (request.type() != null){
            store.setTubeType(request.type());
            changes = true;
        }
        if (request.image() != null){
            store.setItemImageUrl(imageService.saveImage(request.image()));
            changes = true;
        }
        if (!changes){
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
        mainLabTubeStoreDao.updateMainLapTubeStore(store);
    }
}
