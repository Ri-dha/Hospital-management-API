package com.azu.hospital.statical_tables.jobs.dao;

import com.azu.hospital.statical_tables.jobs.entity.JobsFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobsRepository extends JpaRepository<JobsFile,Long> {



    @Query("select j from JobsFile j where " +
            "(:jobTitle is null or j.jobTitle like %:jobTitle%) and " +
            "(:jobCode is null or j.jobCode = :jobCode)")
    Page<JobsFile> searchJobFiles(String jobTitle, Long jobCode, Pageable pageable);



}
