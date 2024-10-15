package com.azu.hospital.statical_tables.chronic_diseases.dao;

import com.azu.hospital.statical_tables.chronic_diseases.entity.ChronicDiseases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChronicDiseasesRepository extends JpaRepository<ChronicDiseases, Long> {

    @Query("select c from ChronicDiseases c where " +
            "(:name is null or c.diseaseName like %:name%) and " +
            "(:diseaseCode is null or c.diseaseCode = :diseaseCode)")
    Page<ChronicDiseases> searchChronicDiseases(String name, Long diseaseCode, Pageable pageable);


}
