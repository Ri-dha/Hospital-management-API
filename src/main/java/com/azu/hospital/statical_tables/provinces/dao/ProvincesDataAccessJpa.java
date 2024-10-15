package com.azu.hospital.statical_tables.provinces.dao;


import com.azu.hospital.statical_tables.provinces.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProvincesRepository")
public class ProvincesDataAccessJpa implements ProvincesDao {
    private final ProvincesRepository provincesRepository;

    @Autowired
    public ProvincesDataAccessJpa(@Qualifier("provincesRepository") ProvincesRepository provincesRepository) {
        this.provincesRepository = provincesRepository;
    }


    @Override
    public void createProvinces(Provinces provinces) {

        provincesRepository.save(provinces);
    }

    @Override
    public void updateProvinces(Provinces provinces) {
        provincesRepository.save(provinces);
    }

    @Override
    public void createProvincesList(List<Provinces> provinces) {
        provincesRepository.saveAll(provinces);
    }

    @Override
    public Page<Provinces> searchProvinces(String name, Long provinceCode, Pageable pageable) {
        return provincesRepository.searchProvinces(name, provinceCode, pageable);
    }

    @Override
    public Page<Provinces> getProvinces(Pageable pageable) {
        return provincesRepository.findAll(pageable);
    }

    @Override
    public Long getProvincesCount() {
        return provincesRepository.count();
    }

    @Override
    public Provinces getProvinceByProvinceCode(Long provinceCode) {
        return provincesRepository.getProvinceByProvinceCode(provinceCode);
    }
}
