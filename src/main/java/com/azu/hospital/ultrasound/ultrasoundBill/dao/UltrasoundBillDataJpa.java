package com.azu.hospital.ultrasound.ultrasoundBill.dao;


import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("UltrasoundBillJpa")
public class UltrasoundBillDataJpa implements UltrasoundBillDao {

    private final UltrasoundBillRepository ultrasoundBillRepository;

    @Autowired
    public UltrasoundBillDataJpa(@Qualifier("ultrasoundBillRepository") UltrasoundBillRepository ultrasoundBillRepository) {
        this.ultrasoundBillRepository = ultrasoundBillRepository;
    }


    @Override
    public void createUltrasoundBill(UltrasoundBill ultrasoundBill) {
       ultrasoundBillRepository.save(ultrasoundBill);
    }

    @Override
    public void updateUltrasoundBill(UltrasoundBill ultrasoundBill) {
        ultrasoundBillRepository.save(ultrasoundBill);
    }

    @Override
    public List<UltrasoundBill> getAllUltrasoundBill() {
        return ultrasoundBillRepository.findAll();
    }

    @Override
    public Optional<UltrasoundBill> getUltrasoundBillById(Long id) {
        return ultrasoundBillRepository.findById(id);
    }

    @Override
    public UltrasoundBill findByType(UltrasoundTypeEnum type) {
        return ultrasoundBillRepository.findByType(type);
    }
}
