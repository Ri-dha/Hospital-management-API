package com.azu.hospital.newPh.Medicins.dao;

import com.azu.hospital.newPh.Medicins.entity.Medicines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicinesDao {

    void insertMedicine(Medicines medicines);
    void createAll(List<Medicines> medicines);
    void updateMedicine(Medicines medicines);

    void deleteMedicine(Long id);

    Optional<Medicines> getMedicineById(Long id);

    Page<Medicines> getAllMedicines(Pageable pageable);

    List<Medicines> getByName(String name);

    Page<Medicines> getAllByFilter(String name, String barCode, Long price, Long quantity, Pageable pageable);

}
