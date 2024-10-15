package com.azu.hospital.Patients.entryTable.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "entry_table")
@Getter
@Setter
public class EntryTable {

    @Id
    @SequenceGenerator(name = "entry_table_id_seq", sequenceName = "entry_table_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_table_id_seq")
    private Long id;


    private Long timesOfEntry;

    private Instant entryDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Patient patient;

    @Column(updatable = false)
    private Instant createdAt;


    @Column(insertable = false)
    private Instant updateAt;




    public EntryTable() {
    }


    public EntryTable(Long timesOfEntry, Instant entryDate) {
        this.timesOfEntry = timesOfEntry;
        this.entryDate = entryDate;
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
