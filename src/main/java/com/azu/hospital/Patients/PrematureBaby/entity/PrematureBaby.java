package com.azu.hospital.Patients.PrematureBaby.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.PrematureBaby.utli.PrematureBabyDate;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;


@Entity
@Table(name = "premature_baby")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PrematureBaby {

    @Id
    @SequenceGenerator(
            name = "premature_baby_sequence",
            sequenceName = "premature_baby_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "premature_baby_sequence"
    )
    private Long id;

    private String name;

    private String headCircumference;

    private Float weight;

    private Float height;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Embedded
    @Valid
    private PrematureBabyDate prematureBabyDate;

    private Boolean isAdmitted = true;
    private Boolean isDischarged = false;
    private Boolean isArchived = false;
    private Boolean isDeleted = false;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updateAt;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Bed bed;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Doctor doctor;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prematureBaby")
//    private List<PediatricAndPregnancyFile> pediatricAndPregnancyFile;


    public PrematureBaby() {
    }

    public PrematureBaby(String name, String headCircumference, Float weight, Float height, Gender gender) {
        this.name = name;
        this.headCircumference = headCircumference;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.prematureBabyDate = new PrematureBabyDate();
        ;
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
