package com.azu.hospital.statical_tables.nations.dao;


import com.azu.hospital.statical_tables.diseases.entity.Diseases;
import com.azu.hospital.statical_tables.nations.entity.Nations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NationsDao {

    void createNations(Nations nations);

    void createNationsList(List<Nations> nations);

    void updateNations(Nations nations);

    Page<Nations> searchNations(String nationsTitle, Long nationsCode, Pageable pageable);

    Page<Nations> getNations(Pageable pageable);

    Long getNationsCount();

}
