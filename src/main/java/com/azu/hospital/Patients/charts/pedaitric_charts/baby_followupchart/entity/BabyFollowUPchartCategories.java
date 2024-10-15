package com.azu.hospital.Patients.charts.pedaitric_charts.baby_followupchart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "baby_follow_up_chart_categories")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BabyFollowUPchartCategories {


        @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String chartName = "Follow - up Chart 1";

    private String rR;
    private String hR;
    private String sPO2;
    private String tEMP;
    private String rBS;
    private String uOP;
    private String meconium;
    private String vomiting;
    private String abdDisiension;
    private String aPNra;
    private String seizure;
    private String primitiveReflexes;
    private String color;
    private String signsOfRespiratoryDistress;
    private String feeding;
    private String tSB_PCV;


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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "baby_follow_up_chart_id")
    private BabyFollowUPchart babyFollowUPchart;

    public BabyFollowUPchartCategories() {
    }

    public BabyFollowUPchartCategories(String rR, String hR, String sPO2, String tEMP, String rBS,
                             String uOP, String meconium, String vomiting, String abdDisiension,
                             String aPNra, String seizure, String primitiveReflexes,
                             String color, String signsOfRespiratoryDistress,
                             String feeding, String tSB_PCV, Long createdBy, Long lastModifiedBy) {
        this.rR = rR;
        this.hR = hR;
        this.sPO2 = sPO2;
        this.tEMP = tEMP;
        this.rBS = rBS;
        this.uOP = uOP;
        this.meconium = meconium;
        this.vomiting = vomiting;
        this.abdDisiension = abdDisiension;
        this.aPNra = aPNra;
        this.seizure = seizure;
        this.primitiveReflexes = primitiveReflexes;
        this.color = color;
        this.signsOfRespiratoryDistress = signsOfRespiratoryDistress;
        this.feeding = feeding;
        this.tSB_PCV = tSB_PCV;
        this.createdBy = createdBy;
        LastModifiedBy = lastModifiedBy;
    }
}
