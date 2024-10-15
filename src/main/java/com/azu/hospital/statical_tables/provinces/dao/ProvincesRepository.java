package com.azu.hospital.statical_tables.provinces.dao;

import com.azu.hospital.statical_tables.provinces.entity.Provinces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProvincesRepository extends JpaRepository<Provinces, Long> {


    @Query("select p from Provinces p where " +
            "(:name is null or p.provinceName like %:name%) and " +
            "(:provinceCode is null or p.provinceCode = :provinceCode)")
    Page<Provinces> searchProvinces(String name, Long provinceCode, Pageable pageable);

    @Query("select p from Provinces p where " +
            " p.provinceCode = :provinceCode")
    Provinces getProvinceByProvinceCode(Long provinceCode);

}
