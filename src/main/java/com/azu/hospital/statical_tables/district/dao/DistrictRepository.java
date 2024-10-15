package com.azu.hospital.statical_tables.district.dao;

import com.azu.hospital.statical_tables.district.entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("select d from District d where " +
            "(:name is null or d.districtName like %:name%) and " +
            "(:districtCode is null or d.districtCode = :districtCode)")
    Page<District> searchDistrict(String name, Long districtCode, Pageable pageable);
}
