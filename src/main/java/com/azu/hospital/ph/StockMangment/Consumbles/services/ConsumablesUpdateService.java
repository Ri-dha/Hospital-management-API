package com.azu.hospital.ph.StockMangment.Consumbles.services;

import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.ConsumableUpdateRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ConsumablesUpdateService {
    private final ConsumableDao consumableDao;

//    private final DateValidations dateValidations;
    private final ImageService imageService;

    public ConsumablesUpdateService(@Qualifier("consumableJpa")
                                    ConsumableDao consumableDao,
                                    ImageService imageService) {
        this.consumableDao = consumableDao;
        this.imageService = imageService;
    }

    public void consumableUpdate(Long consumableId, ConsumableUpdateRequest update) throws IOException {
        Consumables consumables = consumableDao.selectConsumableById(consumableId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Consumable With id [%s] not found".formatted(consumableId)
                ));


        boolean changes = false;
        if (update.consumableName() !=null && !update.consumableName().equals(consumables.getConsumableName())){
            consumables.setConsumableName(update.consumableName());
            changes = true;
        }if (update.consumableCompany() !=null && !update.consumableCompany().equals(consumables.getConsumableCompany())){
            consumables.setConsumableCompany(update.consumableCompany());
            changes = true;
        }if (update.quantity() !=null && !update.quantity().equals(consumables.getQuantity())){
            consumables.setQuantity(update.quantity());
            changes = true;
        }if (update.price() !=null && !update.price().equals(consumables.getPrice())){
            consumables.setPrice(update.price());
            changes = true;
        }if (update.barcode() !=null && !update.barcode().equals(consumables.getBarcode())){
            consumables.setBarcode(update.barcode());
            changes = true;
        }if (update.expDate() !=null && !update.expDate().equals(consumables.getExpDate())){
//            dateValidations.changeDate(update.expDate());
            consumables.setExpDate(update.expDate());
            changes = true;
        }if (update.proDate() !=null && !update.proDate().equals(consumables.getProDate())){
            consumables.setProDate(update.proDate());
            changes = true;
        }if (update.description() !=null && !update.description().equals(consumables.getDescription())){
            consumables.setDescription(update.description());
            changes = true;
        }if (update.typeOfCurrency() !=null && !update.typeOfCurrency().equals(consumables.getTypeOfCurrency())){
            consumables.setTypeOfCurrency(update.typeOfCurrency());
            changes = true;
        }if (update.exchange() !=null && !update.exchange().equals(consumables.getExchange())){
            consumables.setExchange(update.exchange());
            changes = true;
        }if (update.consumablePharmacyPlace() !=null && !update.consumablePharmacyPlace().equals(consumables.getConsumablePharmacyPlace())){
            consumables.setConsumablePharmacyPlace(update.consumablePharmacyPlace());
            changes = true;
        }if (update.consumableImage() !=null){
            consumables.setConsumableImage(imageService.saveImage(update.consumableImage()));
            changes = true;
        }if (!changes) {
            throw new RequestValidationException("No Data changes found");
        }
        consumableDao.updateConsumable(consumables);

    }
}
