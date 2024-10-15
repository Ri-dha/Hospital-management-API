package com.azu.hospital.statical_tables.jobs.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobsDto {
    private Long job_id;
    private String jobTitle;
    private Long jobCode;


    public JobsDto() {
    }



    public JobsDto(Long job_id, String jobTitle, Long jobCode) {
        this.job_id = job_id;
        this.jobTitle = jobTitle;
        this.jobCode = jobCode;
    }

}
