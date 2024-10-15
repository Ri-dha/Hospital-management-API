//package com.azu.hospital.Patients.charts.pedaitric_charts.entity;
//
//
//import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
//import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.ApgarScore;
//import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.PediatricPhysicalExamination;
//import com.azu.hospital.utils.enums.Gender;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.Length;
//import org.hibernate.annotations.CurrentTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.time.Instant;
//import java.util.List;
//
//@Entity
//@Table(name = "pediatric_and_pregnancy_file")
//@Getter
//@Setter
//@EntityListeners(AuditingEntityListener.class)
//public class PediatricAndPregnancyFile {
//
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE
//    )
//    private Long id;
//
//
//    private String chartName = "Pediatric And Pregnancy File";
//
//    private Date dateOfBirth;
//
//    private Time timeOfBirth;
//
//    @Column(columnDefinition = "TEXT")
//    private String modeOfBirth;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//    @Column(columnDefinition = "TEXT")
//    private String gestationalAge;
//
//    @Column(columnDefinition = "TEXT")
//    private String resuscitation;
//
//    @Column(columnDefinition = "TEXT")
//    private String length;
//
//    @Column(columnDefinition = "TEXT")
//    private String headCircumference;
//
//    @Column(columnDefinition = "TEXT")
//    private String weight;
//
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pediatricAndPregnancyFile")
//    private List<ApgarScore> apgarScore;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private PediatricPhysicalExamination pediatricPhysicalExamination;
//
//    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "premature_baby_id")
//    private PrematureBaby prematureBaby;
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
//    @LastModifiedBy
//    @Column(
//            insertable = false
//    )
//    private Long LastModifiedBy;
//
//    public PediatricAndPregnancyFile() {
//    }
//
//    public PediatricAndPregnancyFile(
//            String chartName, Date dateOfBirth,
//            Time timeOfBirth, String modeOfBirth,
//            Gender gender, String gestationalAge,
//            String resuscitation, String length,
//            String headCircumference, String weight
//    ) {
//        this.chartName = chartName;
//        this.dateOfBirth = dateOfBirth;
//        this.timeOfBirth = timeOfBirth;
//        this.modeOfBirth = modeOfBirth;
//        this.gender = gender;
//        this.gestationalAge = gestationalAge;
//        this.resuscitation = resuscitation;
//        this.length = length;
//        this.headCircumference = headCircumference;
//        this.weight = weight;
//    }
//
//}
