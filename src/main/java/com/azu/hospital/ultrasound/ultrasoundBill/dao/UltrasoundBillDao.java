package com.azu.hospital.ultrasound.ultrasoundBill.dao;

import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;

import java.util.List;
import java.util.Optional;

public interface UltrasoundBillDao {

    void createUltrasoundBill(UltrasoundBill ultrasoundBill);

    void updateUltrasoundBill(UltrasoundBill ultrasoundBill);

    List<UltrasoundBill> getAllUltrasoundBill();

    Optional<UltrasoundBill> getUltrasoundBillById(Long id);

    UltrasoundBill findByType(UltrasoundTypeEnum type);

}
