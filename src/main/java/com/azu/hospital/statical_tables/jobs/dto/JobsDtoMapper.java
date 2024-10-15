package com.azu.hospital.statical_tables.jobs.dto;

import com.azu.hospital.statical_tables.jobs.entity.JobsFile;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class JobsDtoMapper implements Function<JobsFile, JobsDto> {
    @Override
    public JobsDto apply(JobsFile jobsFile) {
        return new JobsDto(
                jobsFile.getJob_id(),
                jobsFile.getJobTitle(),
                jobsFile.getJobCode()
        );
    }
}
