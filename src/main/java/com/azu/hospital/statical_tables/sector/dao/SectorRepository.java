package com.azu.hospital.statical_tables.sector.dao;

import com.azu.hospital.statical_tables.sector.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("select s from Sector s where " +
            "(:name is null or s.sectorName like %:name%) and " +
            "(:sectorCode is null or s.sectorCode = :sectorCode)")
    Page<Sector> searchAllBySectorNameAndAndSectorCode(String name, Long sectorCode, Pageable pageable);


}
