package com.azu.hospital.ph.main_data_store.drugs_item.services;

import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.main_data_store.drugs_item.request.DrugItemRegistrationRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DrugItemUpdateServices {


    private final ImageService imageService;
    private final DrugItemDao drugsDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public DrugItemUpdateServices(
            ImageService imageService,
            @Qualifier("DrugItemJpa") DrugItemDao drugsDao, ExceptionsMessageReturn messageReturn) {
        this.imageService = imageService;
        this.drugsDao = drugsDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public void updateDrugItem(@PathVariable Long drugId, @ModelAttribute DrugItemRegistrationRequest updateRequest) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        DrugsItem drugs = drugsDao.selectDrudById(drugId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("DrugsItemNotFound") + " " + drugId

                ));
        boolean changes = false;

        if (updateRequest.drugTradeName() != null && !updateRequest.drugTradeName().equals(drugs.getItemName())) {
            drugs.setItemName(updateRequest.drugTradeName());
            changes = true;
        }
        if (updateRequest.drugScientificName() != null && !updateRequest.drugScientificName().equals(drugs.getDrugScientificName())) {
            drugs.setDrugScientificName(updateRequest.drugScientificName());
            changes = true;
        }
        if (updateRequest.drugCompany() != null && !updateRequest.drugCompany().equals(drugs.getItemCompany())) {
            drugs.setItemCompany(updateRequest.drugCompany());
            changes = true;
        }
        if (updateRequest.dose() != null && !updateRequest.dose().equals(drugs.getDose())) {
            drugs.setDose(updateRequest.dose());
            changes = true;
        }
        if (updateRequest.type() != null && !updateRequest.type().equals(drugs.getType())) {
            drugs.setType(updateRequest.type());
            changes = true;
        }
        if (updateRequest.quantity() != null && !updateRequest.quantity().equals(drugs.getQuantity())) {
            drugs.setQuantity(updateRequest.quantity());
            changes = true;
        }
        if (updateRequest.drugBonus() != null && !updateRequest.drugBonus().equals(drugs.getDrugBonus())) {
            drugs.setDrugBonus(updateRequest.drugBonus());
            changes = true;
        }
        if (updateRequest.description() != null && !updateRequest.description().equals(drugs.getDescription())) {
            drugs.setDescription(updateRequest.description());
            changes = true;
        }
        if (updateRequest.expDate() != null && !updateRequest.expDate().equals(drugs.getExpDate())) {
            drugs.setExpDate(updateRequest.expDate());
            changes = true;
        }
        if (updateRequest.proDate() != null && !updateRequest.proDate().equals(drugs.getProDate())) {
            drugs.setProDate(updateRequest.proDate());
            changes = true;
        }
        if (updateRequest.barcode() != null && !updateRequest.barcode().equals(drugs.getBarcode())) {
            drugs.setBarcode(updateRequest.barcode());
            changes = true;
        }
        if (updateRequest.ndcNumber() != null && !updateRequest.ndcNumber().equals(drugs.getNdcNumber())) {
            drugs.setNdcNumber(updateRequest.ndcNumber());
            changes = true;
        }
        if (updateRequest.price() != null && !updateRequest.price().equals(drugs.getPrice())) {
            drugs.setPrice(updateRequest.price());
            changes = true;
        }
        if (updateRequest.drugSellingPrice() != null && !updateRequest.drugSellingPrice().equals(drugs.getDrugSellingPrice())) {
            drugs.setDrugSellingPrice(updateRequest.drugSellingPrice());
            changes = true;
        }
        if (updateRequest.typOfCurrency() != null && !updateRequest.typOfCurrency().equals(drugs.getTypeOfCurrency())) {
            drugs.setTypeOfCurrency(updateRequest.typOfCurrency());
            changes = true;
        }
        if (updateRequest.exchange() != null && !updateRequest.exchange().equals(drugs.getExchange())) {
            drugs.setExchange(updateRequest.exchange());
            changes = true;
        }
        if (updateRequest.drugImageUrls() != null) {
            drugs.setImageUrls(imageService.saveImage(updateRequest.drugImageUrls()));
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException(
                    messages.getString("noChanges")
            );
        }
        drugsDao.updateDrug(drugs);


    }
}
