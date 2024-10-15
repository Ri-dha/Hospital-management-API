package com.azu.hospital.statical_tables.nations.dao;


import com.azu.hospital.statical_tables.nations.entity.Nations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("NationsRepositoryJpa")
public class NationsDataAccessJpa implements NationsDao {

    private final NationsRepository nationsRepository;

    @Autowired
    public NationsDataAccessJpa(@Qualifier("nationsRepository") NationsRepository nationsRepository) {
        this.nationsRepository = nationsRepository;
    }


    @Override
    public void createNations(Nations nations) {
        nationsRepository.save(nations);
    }

    @Override
    public void createNationsList(List<Nations> nations) {
        nationsRepository.saveAll(nations);
    }

    @Override
    public void updateNations(Nations nations) {
        nationsRepository.save(nations);
    }

    @Override
    public Page<Nations> searchNations(String nationsTitle, Long nationsCode, Pageable pageable) {
        return nationsRepository.searchNations(nationsTitle, nationsCode, pageable);
    }

    @Override
    public Page<Nations> getNations(Pageable pageable) {
        return nationsRepository.findAll(pageable);
    }

    @Override
    public Long getNationsCount() {
        return nationsRepository.count();
    }
}
