package com.azu.hospital.ph.main_data_store.item_archive.services;

import com.azu.hospital.ph.main_data_store.drugs_item.request.DrugItemRegistrationRequest;
import com.azu.hospital.ph.main_data_store.item_archive.dao.DrugItemArchiveDao;
import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("DrugArchiveAddService")
public class DrugItemArchiveAddService {

    private final DrugItemArchiveDao dao;

    @Autowired
    public DrugItemArchiveAddService(
            @Qualifier("DrugItemArchiveJpa") DrugItemArchiveDao dao
    ) {
        this.dao = dao;
    }

    public DrugItemArchive addDrugArchive(DrugItemRegistrationRequest request){
        DrugItemArchive newDrug =  new DrugItemArchive
                .DrugItemArchiveBuilder()
                .itemName(request.drugTradeName())
                .drugScientificName(request.drugScientificName())
                .itemCompany(request.drugCompany())
                .dose(request.dose())
                .barcode(request.barcode())
                .price(request.price())
                .type(request.type())
                .build();
        dao.addDrugToArchives(newDrug);
        return newDrug;
    }

}
