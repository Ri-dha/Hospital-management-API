package com.azu.hospital.statical_tables.jobs.dao;


import com.azu.hospital.statical_tables.jobs.entity.JobsFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JobsJpa")
public class JobsDataAccessJpa implements JobFilesDao {


    private final JobsRepository jobsRepository;

    @Autowired
    public JobsDataAccessJpa(@Qualifier("jobsRepository") JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }


    @Override
    public void createJobFiles(JobsFile jobFiles) {
        jobsRepository.save(jobFiles);
    }

    @Override
    public void updateJobFiles(JobsFile jobFiles) {
        jobsRepository.save(jobFiles);
    }

    @Override
    public void createJobFilesList(List<JobsFile> jobFiles) {
           jobsRepository.saveAll(jobFiles);

    }

    @Override
    public Page<JobsFile> searchJobFiles(String jobTitle, Long jobCode, Pageable pageable) {
        return jobsRepository.searchJobFiles(jobTitle, jobCode, pageable);
    }

    @Override
    public Long getJobsCount() {
        return jobsRepository.count();
    }
}
