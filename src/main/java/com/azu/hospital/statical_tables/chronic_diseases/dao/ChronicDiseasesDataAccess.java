package com.azu.hospital.statical_tables.chronic_diseases.dao;


import com.azu.hospital.statical_tables.chronic_diseases.entity.ChronicDiseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ChronicDiseasesDao")
public class ChronicDiseasesDataAccess implements ChronicDiseasesDao {

    private final ChronicDiseasesRepository chronicDiseasesRepository;

    @Autowired
    public ChronicDiseasesDataAccess(@Qualifier("chronicDiseasesRepository") ChronicDiseasesRepository chronicDiseasesRepository) {
        this.chronicDiseasesRepository = chronicDiseasesRepository;
    }

    @Override
    public void createChronicDiseases(ChronicDiseases chronicDiseases) {
        chronicDiseasesRepository.save(chronicDiseases);
    }

    @Override
    public void updateChronicDiseases(ChronicDiseases chronicDiseases) {
        chronicDiseasesRepository.save(chronicDiseases);
    }

    @Override
    public void createChronicDiseasesList(List<ChronicDiseases> chronicDiseases) {
        chronicDiseasesRepository.saveAll(chronicDiseases);
    }

    @Override
    public Page<ChronicDiseases> searchChronicDiseases(String name, Long diseaseCode, Pageable pageable) {
        return chronicDiseasesRepository.searchChronicDiseases(name, diseaseCode, pageable);
    }

    @Override
    public Long getChronicDiseasesCount() {
        return chronicDiseasesRepository.count();
    }
}
