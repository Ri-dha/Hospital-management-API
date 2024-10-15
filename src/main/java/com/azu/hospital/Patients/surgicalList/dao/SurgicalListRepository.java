package com.azu.hospital.Patients.surgicalList.dao;

import com.azu.hospital.Patients.surgicalList.entity.SurgicalList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

@Transactional
public interface SurgicalListRepository extends JpaRepository<SurgicalList , Long> {

    Page<SurgicalList> getAllBySurgicalDateOrderByState(Instant surgicalDate , Pageable pageable);

}
