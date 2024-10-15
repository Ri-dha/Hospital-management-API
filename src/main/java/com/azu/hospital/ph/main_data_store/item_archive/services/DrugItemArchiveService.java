package com.azu.hospital.ph.main_data_store.item_archive.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.main_data_store.item_archive.dao.DrugItemArchiveDao;
import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class DrugItemArchiveService {
    private final ExceptionsMessageReturn messageReturn;

    private final DrugItemArchiveDao dao;

    @Autowired
    public DrugItemArchiveService(ExceptionsMessageReturn messageReturn, DrugItemArchiveDao dao) {
        this.messageReturn = messageReturn;
        this.dao = dao;
    }

    public Optional<DrugItemArchive> getDrugItemArchiveByBarcode(@RequestParam("barcode") String barcode) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return Optional.ofNullable(dao.findByBarcode(barcode))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }

    public Boolean existDrugItemArchiveByBarcode(@RequestParam("barcode") String barcode){
        return dao.findByBarcode(barcode).isPresent();
    }

}
