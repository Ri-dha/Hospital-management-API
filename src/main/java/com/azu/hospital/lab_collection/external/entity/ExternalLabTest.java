package com.azu.hospital.lab_collection.external.entity;

import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "external_lab_test")
@EntityListeners(AuditingEntityListener.class)
public class ExternalLabTest {
    @Id
    @SequenceGenerator(name = "ext_lab_test_id", sequenceName = "ext_lab_test_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String patientName;
    private String doctorName;

    private Gender gender;

    private String age;

    @Column(updatable = false ,name = "created_at")
    private Instant admissionDate;

    private LabRequestStatusEnum state;

    @Column(columnDefinition = "TEXT")
    private String dx;

    @Column(columnDefinition = "TEXT")
    private String allergy;

    @Column(columnDefinition = "TEXT")
    private String note;

    private BigDecimal height;

    private BigDecimal weight;

    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "externalLabTest")
    private List<ExtTestRequest> testRequests;

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


    public ExternalLabTest() {
    }

    public ExternalLabTest(
            String patientName,
            String doctorName,
            Gender gender,
            String age,
            String dx,
            String allergy,
            LabRequestStatusEnum state,
            String note,
            BigDecimal height,
            BigDecimal weight

    ) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.gender = gender;
        this.age = age;
        this.dx = dx;
        this.allergy = allergy;
        this.state = state;
        this.note = note;
        this.height = height;
        this.weight = weight;
    }




    public String getAdmissionDate() {
        // TODO: 13/10/2023 make the zone as value in applications.properties for change it dynamically

        ZoneId baghdadZone = ZoneId.of("Asia/Baghdad");
        ZonedDateTime now = ZonedDateTime.now(baghdadZone);
        ZonedDateTime admissionDateInBaghdad = admissionDate.atZone(ZoneId.systemDefault()).withZoneSameInstant(baghdadZone);

        if (now.toLocalDate().isEqual(admissionDateInBaghdad.toLocalDate())) {
            return "today " + admissionDateInBaghdad.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else if (now.toLocalDate().minusDays(1).isEqual(admissionDateInBaghdad.toLocalDate())) {
            return "yesterday " + admissionDateInBaghdad.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            return admissionDateInBaghdad.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    @PrePersist
    public void setAdmissionDate() {
        this.admissionDate = Instant.now();
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = Instant.now();
    }


//    public Long getPatientId(){
//        this.patientSpecialI = LocalDate.now().plusDays(1).toEpochDay();
//        return this.patientSpecialI;
//    }


}
