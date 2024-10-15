//package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity;
//
//import com.azu.hospital.Patients.charts.pedaitric_charts.entity.PediatricAndPregnancyFile;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Entity
//@Getter
//@Setter
//@EntityListeners(AuditingEntityListener.class)
//public class ApgarScore {
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE
//    )
//    private Long id;
//
//
//    private String chartName = "Apgar Score";
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "pediatric_and_pregnancy_file_id")
//    private PediatricAndPregnancyFile pediatricAndPregnancyFile;
//
//    public ApgarScore() {
//    }
//}
