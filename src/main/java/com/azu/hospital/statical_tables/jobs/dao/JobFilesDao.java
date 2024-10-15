package com.azu.hospital.statical_tables.jobs.dao;

import com.azu.hospital.statical_tables.jobs.entity.JobsFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobFilesDao {

    void createJobFiles(JobsFile jobFiles);

    void updateJobFiles(JobsFile jobFiles);

    void createJobFilesList(List<JobsFile> jobFiles);

    Page<JobsFile> searchJobFiles(String jobTitle, Long jobCode, Pageable pageable);

    Long getJobsCount();



}
