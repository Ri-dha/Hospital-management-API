package com.azu.hospital.humanResource.employeeDetails.employee.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.EmployeeStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import javax.print.Doc;
import java.time.Instant;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @SequenceGenerator(
            sequenceName = "employee_status_id_sequence",
            name = "employee_status_id_sequence"
    )
    @GeneratedValue(
            generator = "employee_status_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long employeeId;

    @Enumerated(value = EnumType.STRING)
    private EmployeeStatusEnum status;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_id")
    private Permanent permanent;


    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Employee() {
    }

    public Employee(EmployeeStatusEnum status, String note) {
        this.status = status;
        this.note = note;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

}
