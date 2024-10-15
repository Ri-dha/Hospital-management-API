package com.azu.hospital.statical_tables.sector.entity;


import com.azu.hospital.statical_tables.district.entity.District;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "sector")
@Getter
@Setter
public class Sector {
    @Id
    @SequenceGenerator(name = "sector_id_seq", sequenceName = "sector_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_id_seq")
    private Long id;

    private String sectorName;

    private Long sectorCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;


    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;

    public Sector() {
    }

    public Sector(String sectorName, Long sectorCode) {
        this.sectorName = sectorName;
        this.sectorCode = sectorCode;
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
