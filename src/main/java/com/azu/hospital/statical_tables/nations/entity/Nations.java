package com.azu.hospital.statical_tables.nations.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "nations")
@Getter
@Setter
public class Nations {
    @Id
    @SequenceGenerator(name = "nations_id_seq", sequenceName = "nations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nations_id_seq")
    private Long nations_id;

    @Column(name = "nations_title")
    private String nationsTitle;

    private Long nationsCode;

    @Column(updatable = false)
    private Instant createdAt;


    @Column(insertable = false)
    private Instant updateAt;



    public Nations() {
    }

    public Nations(String nationsTitle, Long nationsCode) {
        this.nationsTitle = nationsTitle;
        this.nationsCode = nationsCode;
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
