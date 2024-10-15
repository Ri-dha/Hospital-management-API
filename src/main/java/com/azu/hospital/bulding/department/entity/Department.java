package com.azu.hospital.bulding.department.entity;


import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long depId;

    @NotNull(message = "Department Name Require")
    @NotBlank(message = "Department Name Require")
    @NotEmpty(message = "Department Name Require")
    private String departmentName;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_manager_id")
    private Doctor manager;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_manager_assistance_id")
    private Doctor departmentMangerAssistance;

    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ward> wards = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;


    public Department() {
    }

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Department(
            Long depId,
            String departmentName,
            @Nullable Doctor manager,
            @Nullable Doctor departmentMangerAssistance,
            Floor floor
    ) {
        this.depId = depId;
        this.departmentName = departmentName;
        this.manager = manager;
        this.departmentMangerAssistance = departmentMangerAssistance;
        this.floor = floor;
    }

    public Department(
            String departmentName,
            @Nullable Doctor manager,
            @Nullable Doctor departmentMangerAssistance,
            Floor floor
    ) {
        this.departmentName = departmentName;
        this.manager = manager;
        this.departmentMangerAssistance = departmentMangerAssistance;
        this.floor = floor;
    }


    public Department(String departmentName) {
        this.departmentName = departmentName;
    }


    @PrePersist
    public void setCreateAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updatedAt = Instant.now();
    }



}
