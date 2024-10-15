package com.azu.hospital.statical_tables.diseases.service;


import com.azu.hospital.statical_tables.diseases.dao.DiseasesDao;
import com.azu.hospital.statical_tables.diseases.dto.DiseasesDto;
import com.azu.hospital.statical_tables.diseases.dto.DiseasesDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiseasesGetService {
    private final DiseasesDao diseasesDao;

    private final DiseasesDtoMapper mapper;


    @Autowired
    public DiseasesGetService(
            @Qualifier("diseasesRepositoryJpa") DiseasesDao diseasesDao,
            @Qualifier("diseasesDtoMapper") DiseasesDtoMapper mapper) {
        this.diseasesDao = diseasesDao;
        this.mapper = mapper;
    }


    public Page<DiseasesDto> getDiseases(Pageable pageable) {
        return diseasesDao.getDiseases(pageable).map(mapper);
    }


    public Page<DiseasesDto> searchDiseases(String name, String code, String serialNumber, Pageable pageable) {
        return diseasesDao.searchDiseases(name, code, serialNumber, pageable).map(mapper);
    }

}
