package com.azu.hospital.statical_tables.chronic_diseases.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "chronic_diseases")
@Getter
@Setter
public class ChronicDiseases {

    @Id
    @SequenceGenerator(name = "chronic_diseases_sequence", sequenceName = "chronic_diseases_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chronic_diseases_sequence")
    private Long id;


    @Column(name = "disease_name")
    private String diseaseName;


    @Column(name = "disease_code")
    private Long diseaseCode;


    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;








    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updatedAt = Instant.now();
    }


}
