package com.azu.hospital.statical_tables.diseases.dao;


import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiseasesDao {

    void createDiseases(Diseases diseases);

    void createDiseasesList(List<Diseases> diseases);

    void updateDiseases(Diseases diseases);

    Page<Diseases> searchDiseases(String name, String code, String serialNumber, Pageable pageable);

    Page<Diseases> getDiseases(Pageable pageable);

    Long getDiseasesCount();

}
