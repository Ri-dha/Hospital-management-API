package com.azu.hospital.statical_tables.jobs.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class JobsFile {
    @Id
    @SequenceGenerator(name = "jobs_id_seq", sequenceName = "jobs_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobs_id_seq")
    private Long job_id;

    @Column(name = "job_title")
    private String jobTitle;

    private Long jobCode;

    @Column(updatable = false)
    private Instant createdAt;


    @Column(insertable = false)
    private Instant updateAt;



    public JobsFile() {
    }

    public JobsFile(String jobTitle, Long jobCode) {
        this.jobTitle = jobTitle;
        this.jobCode = jobCode;
    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }


}
