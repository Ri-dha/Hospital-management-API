package com.azu.hospital.statical_tables.sector.dao;


import com.azu.hospital.statical_tables.sector.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectorDao {

    void createProvinces(Sector sector);
    void updateSector(Sector sector);

    void createSectorList(List<Sector> sector);

    Page<Sector> searchSector(String name, Long sectorCode, Pageable pageable);

    Page<Sector> getSector(Pageable pageable);

    Long getSectorCount();

}
