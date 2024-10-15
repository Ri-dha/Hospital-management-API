package com.azu.hospital.statical_tables.diseases.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.time.Instant;

@Entity
@Table(name = "diseases")
@Getter
@Setter
public class Diseases {
    @Id
    @SequenceGenerator(name = "diseases_id_seq", sequenceName = "diseases_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diseases_id_seq")
    private Long id;


    private String code;

    private String name;

    private String serialNumber;

    @Column(updatable = false)
    private Instant createdAt;


    @Column(insertable = false)
    private Instant updateAt;


    public Diseases() {
    }

    public Diseases(String code, String name, String serialNumber) {
        this.code = code;
        this.name = name;
        this.serialNumber = serialNumber;
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
