package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "other_consumables")
@Entity
@Getter
@Setter
public class OtherConsumables {
    @Id
    @SequenceGenerator(
            name = "other_consumables_id_seq",
            sequenceName = "other_consumable_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "other_consumable_id_seq"
    )

    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    private Integer count;

    @Column(columnDefinition = "TEXT")
    private String note;

    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    private Boolean isArchived = false;


    @Column(updatable = false)
    private Instant createAt;
    private Instant updateAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id")
    private OtherConsumablesList otherConsumablesList;

    public OtherConsumables() {
    }

    public OtherConsumables(String name, Integer count, String note) {
        this.name = name;
        this.count = count;
        this.note = note;
    }


    @PrePersist
    public void setCreatedAt() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }

    public Long getOtherConsumablesId() {
        return id;
    }
}
