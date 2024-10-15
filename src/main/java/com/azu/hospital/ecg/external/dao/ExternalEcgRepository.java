package com.azu.hospital.ecg.external.dao;

import com.azu.hospital.ecg.external.entity.ExternalEcg;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Transactional
public interface ExternalEcgRepository extends JpaRepository<ExternalEcg,Long> {


    @Query("SELECT e FROM ExternalEcg e WHERE e.nurse.id = :nurseId ORDER BY e.createdAt DESC")
    List<ExternalEcg> getEcgByNurseNurseIdOrderByCreatedAtDesc(@Param("nurseId") Long nurseId);
    List<ExternalEcg> getEcgByOrderByCreatedAtDesc();
}
