package com.azu.hospital.radiology.internal.InternalRadiologyResult.entity;

import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "internal_radiology_results")
@Getter
@Setter
public class InternalRadiologyResult {

    @Id
    @SequenceGenerator(sequenceName = "internal_radiology_result_id" , name = "internal_radiology_result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "internal_radiology_test")
    private InternalRadiologyTest internalRadiologyTest;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<File> files; // TODO: 12/29/2023 make it urls save in same table and remove this relations

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;
    public InternalRadiologyResult() {
    }


    public InternalRadiologyResult(String note) {
        this.note = note;
    }
}
