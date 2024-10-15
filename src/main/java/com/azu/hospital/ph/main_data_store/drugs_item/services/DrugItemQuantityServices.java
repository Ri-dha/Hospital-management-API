//package com.azu.hospital.ph.main_data_store.drugs_item.services;
//
//import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
//import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DrugItemQuantityServices {
//
//    private final DrugItemDao drugItemDao;
//
//    @Autowired
//    public DrugItemQuantityServices(@Qualifier("DrugItemJpa") DrugItemDao drugItemDao) {
//        this.drugItemDao = drugItemDao;
//    }
//
//    @Scheduled(fixedRate = 60000)
//    public void updateDrugExistStatus() {
//        List<DrugsItem> allDrugs = drugItemDao.selectAllDrugsForSetQuantity();
//
//        for (DrugsItem drug : allDrugs) {
//            if (drug.getQuantity() <= 0) {
//                drug.setIsDrugExist(false);
//                drugItemDao.updateDrug(drug);
//            }
//        }
//    }
//}
