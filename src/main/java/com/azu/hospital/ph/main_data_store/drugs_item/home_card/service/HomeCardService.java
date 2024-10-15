package com.azu.hospital.ph.main_data_store.drugs_item.home_card.service;


import com.azu.hospital.ph.StockMangment.Offers.Dao.OfferDao;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.home_card.dto.HomeCardDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import org.springframework.stereotype.Service;

@Service
public class HomeCardService {

    private final DrugItemDao drugItemDao;

    private final OfferDao offerDao;
    private final DrugRequestHandlerDao drugRequestHandlerDao;
    public HomeCardService(DrugItemDao drugItemDao, OfferDao offerDao, DrugRequestHandlerDao drugRequestHandlerDao) {
        this.drugItemDao = drugItemDao;
        this.offerDao = offerDao;
        this.drugRequestHandlerDao = drugRequestHandlerDao;
    }

    public HomeCardDto getHomeCardCounts() {
        Long totalDrugs = drugItemDao.countAllDrug();
        Long totalDrugRequests = drugRequestHandlerDao.repositoryCount();
        Long totalDrugBack = drugItemDao.countAllDrugBack();
        Long totalDrugSold = drugItemDao.countAllDrugSold();
        Long totalDrugExpire = drugItemDao.countAllDrugExpire();
        Long totalOffers = offerDao.countAllOffers();

        return new HomeCardDto(
                totalDrugs,
                totalDrugRequests,
                totalDrugBack,
                totalDrugSold,
                totalDrugExpire,
                totalOffers
        );
    }


}
