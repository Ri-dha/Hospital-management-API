package com.azu.hospital.statical_tables.diseases.dao;


import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("diseasesRepositoryJpa")
public class DiseasesDataAccessJpa implements DiseasesDao {
    private final DiseasesRepository diseasesRepository;

    @Autowired
    public DiseasesDataAccessJpa(@Qualifier("diseasesRepository") DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }


    @Override
    public void createDiseases(Diseases diseases) {
        diseasesRepository.save(diseases);
    }

    @Override
    public void createDiseasesList(List<Diseases> diseases) {
        diseasesRepository.saveAll(diseases);
    }

    @Override
    public void updateDiseases(Diseases diseases) {
        diseasesRepository.save(diseases);
    }

    @Override
    public Page<Diseases> searchDiseases(String name, String code, String serialNumber, Pageable pageable) {
        return diseasesRepository.searchDiseases(name, code, serialNumber, pageable);
    }

    @Override
    public Page<Diseases> getDiseases(Pageable pageable) {
        return diseasesRepository.findAll(pageable);
    }

    @Override
    public Long getDiseasesCount() {
        return diseasesRepository.count();
    }
}
