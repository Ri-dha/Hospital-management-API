package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity;

import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "baby_doctor_tour")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BabyDoctorTour {

    @Id
    @SequenceGenerator(
            name = "baby_doctor_tour_sequence",
            sequenceName = "baby_doctor_tour_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "baby_doctor_tour_sequence"
    )
    private Long id;



    private String note;



    private String medicalDx;

    private String tourDetails;

    private String followUpNote;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updateAt;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private PrematureBaby prematureBaby;


    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;
    @org.springframework.data.annotation.LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    public BabyDoctorTour() {
    }

    public BabyDoctorTour(String note, String medicalDx, String tourDetails, String followUpNote) {

        this.note = note;

        this.medicalDx = medicalDx;
        this.tourDetails = tourDetails;
        this.followUpNote = followUpNote;
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
