package com.azu.hospital.radiology.external.ExternalRadiologyResult.entity;

import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "external_radiology_results")
@Getter
@Setter
public class ExternalRadiologyResult {

    @Id
    @SequenceGenerator(sequenceName = "external_radiology_result_id", name = "external_radiology_result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String note;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExternalRadiologyTest externalRadiologyTest;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ExternalRadiologyResult() {
    }


    public ExternalRadiologyResult(String note) {
        this.note = note;
    }

}
