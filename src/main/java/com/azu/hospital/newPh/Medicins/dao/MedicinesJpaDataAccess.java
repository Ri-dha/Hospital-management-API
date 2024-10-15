package com.azu.hospital.newPh.Medicins.dao;


import com.azu.hospital.newPh.Medicins.entity.Medicines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MedicinesRepository")
public class MedicinesJpaDataAccess implements MedicinesDao {


    private final MedicinesRepository medicinesRepository;

    @Autowired
    public MedicinesJpaDataAccess(@Qualifier("medicinesRepository") MedicinesRepository medicinesRepository) {
        this.medicinesRepository = medicinesRepository;
    }


    @Override
    public void insertMedicine(Medicines medicines) {
        medicinesRepository.save(medicines);
    }

    @Override
    public void createAll(List<Medicines> medicines) {
        medicinesRepository.saveAll(medicines);
    }

    @Override
    public void updateMedicine(Medicines medicines) {
        medicinesRepository.save(medicines);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicinesRepository.deleteById(id);
    }

    @Override
    public Optional<Medicines> getMedicineById(Long id) {
        return medicinesRepository.findById(id);
    }

    @Override
    public Page<Medicines> getAllMedicines(Pageable pageable) {
        return medicinesRepository.findAll(pageable);
    }

    @Override
    public List<Medicines> getByName(String name) {
        return medicinesRepository.getByName(name);
    }

    @Override
    public Page<Medicines> getAllByFilter(String name, String barCode, Long price, Long quantity, Pageable pageable) {
        return medicinesRepository.getAllByFilter(name, barCode, price, quantity, pageable);
    }
}
