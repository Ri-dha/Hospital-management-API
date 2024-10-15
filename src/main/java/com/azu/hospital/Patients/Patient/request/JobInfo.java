package com.azu.hospital.Patients.Patient.request;

import com.azu.hospital.utils.enums.patient.JopTypeEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class JobInfo {

    @NotNull(message = "Job must not be null")
    @NotBlank(message = "Job must not be blank")
    private String job;

    @NotNull(message = "jobType must not be null")
    @Enumerated
    private JopTypeEnum jobType;


    public JobInfo() {
    }

    public JobInfo(String job, JopTypeEnum jobType) {
        this.job = job;
        this.jobType = jobType;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public JopTypeEnum getJobType() {
        return jobType;
    }

    public void setJobType(JopTypeEnum jobType) {
        this.jobType = jobType;
    }
}
