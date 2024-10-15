package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DrugIsBackDtoMapper implements Function<DrugsItem, DrugIsBackDto> {
    @Override
    public DrugIsBackDto apply(DrugsItem drugsItem) {
        DrugRequestHandler firstHandler = drugsItem.getFirstDrugRequestHandler();


        Long patientId = null;
        Long billListId = null;

        if (firstHandler != null && firstHandler.getPatient() != null) {
            patientId = firstHandler.getPatient().getId();
        }

        if (!drugsItem.getSoldItems().isEmpty()){
            billListId = drugsItem.getSoldItems().stream().map(SoldItems::getId).findFirst().orElse(null);
        }

        return new DrugIsBackDto(
                drugsItem.getId(),
                drugsItem.getItemName(),
                drugsItem.getDrugScientificName(),
                drugsItem.getItemCompany(),
                drugsItem.getDose(),
                drugsItem.getType(),
                drugsItem.getQuantity(),
                drugsItem.getDrugBonus(),
                drugsItem.getDescription(),
                drugsItem.getExpDate(),
                drugsItem.getProDate(),
                drugsItem.getBarcode(),
                drugsItem.getNdcNumber(),
                drugsItem.getPrice(),
                drugsItem.getDrugSellingPrice(),
                drugsItem.getTypeOfCurrency(),
                drugsItem.getExchange(),
                drugsItem.getIsBack(),
                drugsItem.getIsDrugExist(),
                drugsItem.getImageUrls(),
                1L,
                drugsItem.getUpdateAt(),
                patientId,
                drugsItem.getFirstDrugRequestHandler() == null ? null : drugsItem.getFirstDrugRequestHandler().getNote(),
                billListId
        );
    }
}

