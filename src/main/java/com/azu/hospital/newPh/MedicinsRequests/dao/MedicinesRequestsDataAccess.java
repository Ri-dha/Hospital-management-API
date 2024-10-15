package com.azu.hospital.newPh.MedicinsRequests.dao;


import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MedicinesRequestsDataJpa")
public class MedicinesRequestsDataAccess implements MedicinesRequestsDao {

    private final MedicinesRequestsRepository repository;

    public MedicinesRequestsDataAccess(MedicinesRequestsRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<MedicinesRequests> createMedicinesRequests(List<MedicinesRequests> medicinesRequests) {
        return repository.saveAll(medicinesRequests);
    }

    @Override
    public void updateMedicinesRequests(MedicinesRequests update) {
        repository.save(update);
    }

    @Override
    public void deleteMedicinesRequestsById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MedicinesRequests> getMedicinesRequestsById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<MedicinesRequests> getAllMedicinesRequests(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<MedicinesRequests> getAllMedicinesRequestsByStatusAndLastUpdateAsd(UnitInventoryRequestEnum status, Pageable pageable) {
        return repository.getAllMedicinesRequestsByStatusAndLastUpdateAsd(status, pageable);
    }
}
