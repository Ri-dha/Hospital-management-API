package com.azu.hospital.ecg.ecgbill.dao;


import com.azu.hospital.ecg.ecgbill.entity.EcgBill;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ecgBillDataJpa")
public class EcgBillDataJpa implements EcgBillDao {

    private final EcgBillRepository ecgBillRepository;

    @Autowired
    public EcgBillDataJpa(@Qualifier("ecgBillRepository") EcgBillRepository ecgBillRepository) {
        this.ecgBillRepository = ecgBillRepository;
    }


    @Override
    public void createEcgBill(EcgBill ecgBill) {
       ecgBillRepository.save(ecgBill);
    }

    @Override
    public void updateEcgBill(EcgBill ecgBill) {
        ecgBillRepository.save(ecgBill);
    }

    @Override
    public List<EcgBill> getAllEcgBill() {
        return ecgBillRepository.findAll();
    }

    @Override
    public Optional<EcgBill> getExgBillById(Long id) {
        return ecgBillRepository.findById(id);
    }



}
