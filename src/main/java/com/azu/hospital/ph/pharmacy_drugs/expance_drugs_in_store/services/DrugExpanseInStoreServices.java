package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.services;

import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao.DrugExpanseInStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugExpanseInStoreServices {

    private final DrugExpanseInStoreDao dao;

    @Autowired
    public DrugExpanseInStoreServices(DrugExpanseInStoreDao dao) {
        this.dao = dao;
    }

    public void addDrugToExpanseTable(Long existId, Integer quantity){

    }
}
