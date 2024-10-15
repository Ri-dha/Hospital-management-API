package com.azu.hospital.statical_tables.district.entity;


import com.azu.hospital.statical_tables.provinces.entity.Provinces;
import com.azu.hospital.statical_tables.sector.entity.Sector;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "district")
@Getter
@Setter
public class District {
    @Id
    @SequenceGenerator(name = "district_id_seq", sequenceName = "district_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_seq")
    private Long id;


    private String districtName;

    private Long districtCode;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id")
    private Provinces provinces;

    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Sector> sectors;

    public District() {
    }


    public District(String districtName, Long districtCode) {
        this.districtName = districtName;
        this.districtCode = districtCode;
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
