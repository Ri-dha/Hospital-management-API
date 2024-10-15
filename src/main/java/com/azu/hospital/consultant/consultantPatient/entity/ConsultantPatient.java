package com.azu.hospital.consultant.consultantPatient.entity;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientAddress;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientInfo;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientJobInfo;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.image.Image;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultant_patients")
@Getter
@Setter
public class ConsultantPatient {

    @Id
    @SequenceGenerator(name = "consultant_patients", sequenceName = "consultant_patients", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Embedded
    private ConsultantPatientInfo consultantPatientInfo;

    @Embedded
    private ConsultantPatientAddress consultantPatientAddress;

    @Embedded
    private ConsultantPatientJobInfo consultantPatientJobInfo;

    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public ConsultantPatient(
            ConsultantPatientInfo consultantPatientInfo,
            ConsultantPatientAddress consultantPatientAddress,
            ConsultantPatientJobInfo consultantPatientJobInfo,
            String note
    ) {
        this.consultantPatientInfo = consultantPatientInfo;
        this.consultantPatientAddress = consultantPatientAddress;
        this.consultantPatientJobInfo = consultantPatientJobInfo;
        this.note = note;
    }

    public ConsultantPatient() {

    }

    public ConsultantPatient(
            ConsultantPatientInfo consultantPatientInfo,
            ConsultantPatientAddress consultantPatientAddress,
            ConsultantPatientJobInfo consultantPatientJobInfo,
            String note,
            Doctor doctor
    ) {
        this.consultantPatientInfo = consultantPatientInfo;
        this.consultantPatientAddress = consultantPatientAddress;
        this.consultantPatientJobInfo = consultantPatientJobInfo;
        this.note = note;
        this.doctor = doctor;
    }

    public static ConsultantPatientBuilder builder() {
        return new ConsultantPatientBuilder();
    }


    public static class ConsultantPatientBuilder {
        private ConsultantPatientInfo consultantPatientInfo;
        private ConsultantPatientAddress consultantPatientAddress;
        private ConsultantPatientJobInfo consultantPatientJobInfo;
        private String note;
        private Doctor doctor;


        ConsultantPatientBuilder() {
        }


        public ConsultantPatientBuilder consultantPatientInfo(ConsultantPatientInfo consultantPatientInfo) {
            this.consultantPatientInfo = consultantPatientInfo;
            return this;
        }

        public ConsultantPatientBuilder consultantPatientAddress(ConsultantPatientAddress consultantPatientAddress) {
            this.consultantPatientAddress = consultantPatientAddress;
            return this;
        }

        public ConsultantPatientBuilder consultantPatientJobInfo(ConsultantPatientJobInfo consultantPatientJobInfo) {
            this.consultantPatientJobInfo = consultantPatientJobInfo;
            return this;
        }

        public ConsultantPatientBuilder note(String note) {
            this.note = note;
            return this;
        }

        public ConsultantPatientBuilder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public ConsultantPatient build() {
            return new ConsultantPatient(
                    this.consultantPatientInfo,
                    this.consultantPatientAddress,
                    this.consultantPatientJobInfo,
                    this.note,
                    this.doctor
            );
        }
    }
}
