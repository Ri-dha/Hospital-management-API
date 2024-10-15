package com.azu.hospital.statical_tables.provinces.dao;

import com.azu.hospital.statical_tables.provinces.entity.Provinces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProvincesDao {

    void createProvinces(Provinces provinces);
    void updateProvinces(Provinces provinces);

    void createProvincesList(List<Provinces> provinces);

    Page<Provinces> searchProvinces(String name, Long provinceCode, Pageable pageable);

    Page<Provinces> getProvinces(Pageable pageable);

    Long getProvincesCount();


    Provinces getProvinceByProvinceCode(Long provinceCode);


}
