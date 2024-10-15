package com.azu.hospital.bulding.floor.entity;

import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floor")
@Getter
@Setter
public class Floor {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long floorId;
    @NotNull(message = "Floor Number Require")
    private String floorNumber;
    @NotNull(message = "Floor Name Require")
    @NotEmpty(message = "Floor Name Require")
    @NotBlank(message = "Floor Name Require")
    private String floorName;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Department> department;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ward> wards = new ArrayList<>();



    public Floor() {
    }

    public Floor(Long floorId, String floorNumber, String floorName) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.floorName = floorName;
    }

    public Floor(String floorNumber, String floorName) {
        this.floorNumber = floorNumber;
        this.floorName = floorName;
    }

    public Floor(Long floorId) {
        this.floorId = floorId;
    }


    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }


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


}
