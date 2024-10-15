package com.azu.hospital.ecg.ecgbill.dao;

import com.azu.hospital.ecg.ecgbill.entity.EcgBill;

import java.util.List;
import java.util.Optional;

public interface EcgBillDao {

    void createEcgBill(EcgBill ecgBill);

    void updateEcgBill(EcgBill ecgBill);

    List<EcgBill> getAllEcgBill();

    Optional<EcgBill> getExgBillById(Long id);




}
