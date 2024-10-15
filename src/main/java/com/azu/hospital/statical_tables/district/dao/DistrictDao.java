package com.azu.hospital.statical_tables.district.dao;


import com.azu.hospital.statical_tables.district.entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DistrictDao {

    void createProvinces(District district);
    void updateDistrict(District district);

    void createDistrictList(List<District> district);

    Page<District> searchDistrict(String name, Long districtCode, Pageable pageable);

    Page<District> getDistrict(Pageable pageable);

    Long getDistrictCount();

}
