package com.azu.hospital.consultant.appointment.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.utils.enums.appointment.AppointmentNewOrReview;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "appointment_id_seq" , name = "appointment_id_seq")
    private Long id;

    private Integer number;

    private Instant date;

    private AppointmentsState state;


    private AppointmentNewOrReview patientState;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private ConsultantPatient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Appointment() {
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public Appointment(
            Integer number,
            Instant date,
            AppointmentsState state,
            AppointmentNewOrReview patientState
    ) {
        this.number = number;
        this.date = date;
        this.state = state;
        this.patientState = patientState;
    }
}
