package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dao;

import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("MedicinesRequestsListJpa")
public class MedicinesRequestsListJpaDataAccess implements MedicinesRequestsListDao{

    private final MedicinesRequestsListRepository repository;

    @Autowired
    public MedicinesRequestsListJpaDataAccess(MedicinesRequestsListRepository medicinesRequestsListRepository) {
        this.repository = medicinesRequestsListRepository;
    }


    @Override
    public MedicinesRequestsList addMedicinesRequestsList(MedicinesRequestsList medicinesRequestsList) {
        return repository.save(medicinesRequestsList);
    }

    @Override
    public void updateMedicinesRequestsList(MedicinesRequestsList medicinesRequestsList) {
            repository.save(medicinesRequestsList);
    }

    @Override
    public Optional<MedicinesRequestsList> getMedicinesRequestsListById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<MedicinesRequestsList> getMedicinesRequestsListAllWithFilter(String patientName, Pageable pageable) {
        return repository.getAllByPatientPatientName(patientName, pageable);
    }

    @Override
    public Page<MedicinesRequestsList> getMedicinesRequestsListAccordingToUpdatedAt(Pageable pageable) {
        return repository.getMedicinesRequestsListAccordingToUpdatedAt(pageable);
    }

    @Override
    public Page<MedicinesRequestsList> getAllByPatientId(Long patientId, Pageable pageable) {
        return null;
    }
}
