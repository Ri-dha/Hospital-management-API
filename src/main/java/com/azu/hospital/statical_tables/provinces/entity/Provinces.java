package com.azu.hospital.statical_tables.provinces.entity;


import com.azu.hospital.statical_tables.district.entity.District;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
public class Provinces {
    @Id
    @SequenceGenerator(name = "provinces_id_seq", sequenceName = "provinces_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provinces_id_seq")
    private Long id;


    private String provinceName;

    private Long provinceCode;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "provinces",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<District> district;


    public Provinces() {
    }

    public Provinces(String provinceName, Long provinceCode) {
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updatedAt = Instant.now();
    }
}


