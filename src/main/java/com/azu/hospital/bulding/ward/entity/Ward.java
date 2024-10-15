package com.azu.hospital.bulding.ward.entity;


import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wards")
@Getter
@Setter
public class Ward {

    @Id
    @SequenceGenerator(name = "ward_unit_id", sequenceName = "ward_unit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ward_unit_id")

    private Long wardId;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ward", fetch = FetchType.LAZY)
    private List<WardDevice> wardDevices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ward", fetch = FetchType.LAZY)
    private List<WardMedicalDevice> wardMedicalDevices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ward", fetch = FetchType.LAZY)
    private List<WardFurniture> wardFurniture = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ward", fetch = FetchType.LAZY)
    private List<Bed> beds;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
    private List<ConsumablesRequestTable> consumablesRequestTable;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ward")
    private List<ConsumablesRequestTableList> consumablesRequestTableList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ward")
    private List<CostumeShift> costumeShifts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserShiftsTable> userShiftsTablesWards;

    public Ward() {
    }

    public Ward(String name) {
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
