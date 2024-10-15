package com.azu.hospital.statical_tables.diseases.dao;

import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiseasesRepository extends JpaRepository<Diseases,Long> {


    @Query("select d from Diseases d where " +
            "(:name is null or d.name like %:name%) and " +
            "(:code is null or d.code like %:code%) and " +
            "(:serialNumber is null or d.serialNumber like %:serialNumber%)")
    Page<Diseases> searchDiseases(String name, String code, String serialNumber, Pageable pageable);

}
