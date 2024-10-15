//package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CurrentTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.Instant;
//
//@Entity
//@Table(name = "pediatric_physical_examination")
//@Getter
//@Setter
//@EntityListeners(AuditingEntityListener.class)
//public class PediatricPhysicalExamination {
//
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE
//    )
//    private Long id;
//
//    private String chartName = "Pediatric Physical Examination";
//
//    @Column(columnDefinition = "TEXT")
//    private String color;
//
//    @Column(columnDefinition = "TEXT")
//    private String cry;
//
//    @Column(columnDefinition = "TEXT")
//    private String head;
//
//    @Column(columnDefinition = "TEXT")
//    private String fontanelle;
//
//    @Column(columnDefinition = "TEXT")
//    private String eyes;
//
//    @Column(columnDefinition = "TEXT")
//    private String nose;
//
//    @Column(columnDefinition = "TEXT")
//    private String mouth;
//
//    @Column(columnDefinition = "TEXT")
//    private String neck;
//
//    @Column(columnDefinition = "TEXT")
//    private String clavicle;
//
//    @Column(columnDefinition = "TEXT")
//    private String breast;
//
//    @Column(columnDefinition = "TEXT")
//    private String thorax;
//
//    @Column(columnDefinition = "TEXT")
//    private String lung;
//
//    @Column(columnDefinition = "TEXT")
//    private String heart;
//
//    @Column(columnDefinition = "TEXT")
//    private String abdomen;
//
//    @Column(columnDefinition = "TEXT")
//    private String liver;
//
//    @Column(columnDefinition = "TEXT")
//    private String spleen;
//
//  @Column(columnDefinition = "TEXT")
//    private String activity;
//
//
//  @Transient
//  private String deformities = "Deformities and Malformations";
//
//
//    @Column(columnDefinition = "TEXT")
//    private String hernia;
//
//    @Column(columnDefinition = "TEXT")
//    private String umbilicus;
//
//    @Column(columnDefinition = "TEXT")
//    private String genitalia;
//
//    @Column(columnDefinition = "TEXT")
//    private String rectum;
//
//    @Column(columnDefinition = "TEXT")
//    private String skin;
//
//    @Column(columnDefinition = "TEXT")
//    private String extremities;
//
//    @Column(columnDefinition = "TEXT")
//    private String soleCreases;
//
//    @Column(columnDefinition = "TEXT")
//    private String reflexes;
//
//    @Column(columnDefinition = "TEXT")
//    private String moro;
//
//    @Column(columnDefinition = "TEXT")
//    private String grasp;
//
//    @Column(columnDefinition = "TEXT")
//    private String light;
//
//    @Column(columnDefinition = "TEXT")
//    private String glabellar;
//
//    @Column(columnDefinition = "TEXT")
//    private String rooting;
//
//    @Column(columnDefinition = "TEXT")
//    private String sucking;
//
//
//
//
//    @CurrentTimestamp
//    @Column(
//            nullable = false,
//            updatable = false
//    )
//    private Instant createdAt;
//
//    @UpdateTimestamp
//    @Column(
//            insertable = false
//    )
//    private Instant updatedAt;
//
//
//    @CreatedBy
//    @Column(
//            nullable = false,
//            updatable = false
//    )
//    private Long createdBy;
//    @org.springframework.data.annotation.LastModifiedBy
//    @Column(
//            insertable = false
//    )
//    private Long LastModifiedBy;
//
//
//    public PediatricPhysicalExamination() {
//    }
//
//
//}
