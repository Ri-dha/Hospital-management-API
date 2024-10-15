package com.azu.hospital.bulding.unit.entity;


import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "units")
@Getter
@Setter
public class Unit {

    @Id
    @SequenceGenerator(name = "ward_unit_id", sequenceName = "ward_unit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ward_unit_id")
    private Long unitId;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Doctor manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assistance_id")
//    @JsonIgnore
    private BaseUser managerAssistance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore

    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UnitDevice> unitDevices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unit")
    private List<ConsumablesRequestTable> consumablesRequestTable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unit", fetch = FetchType.LAZY)
    private List<ConsumablesRequestTableList> consumablesRequestTableLists;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unit", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UnitFurniture> unitFurniture = new ArrayList<>();


    private Instant createdAt;

    private Instant updatedAt;


    public Unit() {
    }

    public Unit(String name) {
        this.name = name;
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
