package com.azu.hospital.statical_tables.chronic_diseases.dao;

import com.azu.hospital.statical_tables.chronic_diseases.entity.ChronicDiseases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChronicDiseasesDao {

    void createChronicDiseases(ChronicDiseases chronicDiseases);

    void updateChronicDiseases(ChronicDiseases chronicDiseases);

    void createChronicDiseasesList(List<ChronicDiseases> chronicDiseases);

    Page<ChronicDiseases> searchChronicDiseases(String name, Long diseaseCode, Pageable pageable);


    Long getChronicDiseasesCount();

}
