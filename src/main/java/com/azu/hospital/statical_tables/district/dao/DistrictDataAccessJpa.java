package com.azu.hospital.statical_tables.district.dao;


import com.azu.hospital.statical_tables.district.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("districtrepo")
public class DistrictDataAccessJpa implements DistrictDao {

    private final DistrictRepository repository;

    @Autowired
    public DistrictDataAccessJpa(@Qualifier("districtRepository") DistrictRepository districtRepository) {
        this.repository = districtRepository;
    }


    @Override
    public void createProvinces(District provinces) {
        repository.save(provinces);
    }

    @Override
    public void updateDistrict(District provinces) {
        repository.save(provinces);
    }

    @Override
    public void createDistrictList(List<District> provinces) {
        repository.saveAll(provinces);
    }

    @Override
    public Page<District> searchDistrict(String name, Long districtCode, Pageable pageable) {
        return repository.searchDistrict(name, districtCode, pageable);
    }

    @Override
    public Page<District> getDistrict(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long getDistrictCount() {
        return repository.count();
    }
}
