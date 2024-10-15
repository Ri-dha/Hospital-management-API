package com.azu.hospital.Patients.surgicalList.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.patient.AnesthesiaEnum;
import com.azu.hospital.utils.enums.patient.SurgicalStateEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "surgical_lists")
public class SurgicalList {

    @Id
    @SequenceGenerator(name = "surgical_list_id_seq" , sequenceName = "surgical_list_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String surgicalName;

    private AnesthesiaEnum anesthesiaType;

    private SurgicalStateEnum state = SurgicalStateEnum.Waitting;

    private Instant surgicalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();


    private Instant createdAt;

    private Instant updatedAt;

    public SurgicalList() {
    }

    public SurgicalList(
            String surgicalName,
            AnesthesiaEnum anesthesiaType,
            Instant surgicalDate

    ) {
        this.surgicalName = surgicalName;
        this.anesthesiaType = anesthesiaType;
        this.surgicalDate = surgicalDate;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }



}
