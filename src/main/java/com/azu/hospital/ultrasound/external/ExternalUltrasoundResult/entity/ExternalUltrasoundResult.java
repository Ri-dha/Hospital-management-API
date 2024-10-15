package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity;

import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "external_ultrasound_results")
@Getter
@Setter
public class ExternalUltrasoundResult {

    @Id
    @SequenceGenerator(sequenceName = "external_ultrasound_result_id" , name = "external_ultrasound_result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne
    private ExternalUltrasoundTest externalUltrasoundTest;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<File> files;

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

    public ExternalUltrasoundResult() {
    }


    public ExternalUltrasoundResult(String note) {
        this.note = note;
    }
}
