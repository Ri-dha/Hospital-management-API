package com.azu.hospital.statical_tables.sector.dao;


import com.azu.hospital.statical_tables.sector.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sectorRepositoryJpa")
public class SectorDataAccessJpa implements SectorDao {
    private final SectorRepository repository;

    @Autowired
    public SectorDataAccessJpa(@Qualifier("sectorRepository") SectorRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createProvinces(Sector sector) {
        repository.save(sector);
    }

    @Override
    public void updateSector(Sector sector) {
        repository.save(sector);
    }

    @Override
    public void createSectorList(List<Sector> sector) {
        repository.saveAll(sector);
    }

    @Override
    public Page<Sector> searchSector(String name, Long sectorCode, Pageable pageable) {
        return  repository.searchAllBySectorNameAndAndSectorCode(name, sectorCode, pageable);
    }

    @Override
    public Page<Sector> getSector(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long getSectorCount() {
        return repository.count();
    }
}
