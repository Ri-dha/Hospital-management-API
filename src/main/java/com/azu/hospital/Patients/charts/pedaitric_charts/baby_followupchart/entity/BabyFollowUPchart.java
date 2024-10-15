package com.azu.hospital.Patients.charts.pedaitric_charts.baby_followupchart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "baby_follow_up_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BabyFollowUPchart {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String chartName = "Follow - up Chart 1";
    private String motherName;
    private Long motherId;
    private String wT;

    @Column(columnDefinition = "TEXT")
    private String systemicExamination;

    @Column(columnDefinition = "TEXT")
    private String oral;

    @Column(columnDefinition = "TEXT")
    private String eyes;

    @Column(columnDefinition = "TEXT")
    private String head;

    @Column(columnDefinition = "TEXT")
    private String umbilical;

    @Column(columnDefinition = "TEXT")
    private String back;

    @Column(columnDefinition = "TEXT")
    private String genitalia;

    @Column(columnDefinition = "TEXT")
    private String cannula;


    @OneToMany(
            mappedBy = "babyFollowUPchart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<BabyFollowUPchartCategories> babyFollowUPchartCategories;



    @CurrentTimestamp
    @Column(
            nullable = false,
            updatable = false
    )
    private Instant createdAt;

    @UpdateTimestamp
    @Column(
            insertable = false
    )
    private Instant updatedAt;


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

    public BabyFollowUPchart() {
    }

    public BabyFollowUPchart(String motherName, Long motherId,
                             String wT, String systemicExamination,
                             String oral, String eyes, String head,
                             String umbilical, String back, String genitalia,
                             String cannula) {
        this.motherName = motherName;
        this.motherId = motherId;
        this.wT = wT;
        this.systemicExamination = systemicExamination;
        this.oral = oral;
        this.eyes = eyes;
        this.head = head;
        this.umbilical = umbilical;
        this.back = back;
        this.genitalia = genitalia;
        this.cannula = cannula;
    }
}
