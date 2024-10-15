package com.azu.hospital.statical_tables.nations.dao;

import com.azu.hospital.statical_tables.nations.entity.Nations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NationsRepository extends JpaRepository<Nations,Long> {


    @Query("select n from Nations n where " +
            "(:nationsTitle is null or n.nationsTitle like %:nationsTitle%) and " +
            "(:nationsCode is null or n.nationsCode = :nationsCode)")
    Page<Nations> searchNations(String nationsTitle, Long nationsCode, Pageable pageable);


}
