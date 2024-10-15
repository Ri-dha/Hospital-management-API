package com.azu.hospital.bulding.Shift.entity;


import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import com.azu.hospital.utils.enums.Shift.ShiftType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "shifts_id_seq", sequenceName = "shifts_id_seq")
    private Long id;


    private String name;

    private String fromTime;

    private String toTime;

    private ShiftType shiftType;


    @OneToMany(mappedBy = "shift" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<EmployeeShift> employeeShifts;

    private Instant createdAt;

    private Instant updatedAt;

    public Shift(String name, String fromTime, String toTime , ShiftType shiftType) {
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.shiftType = shiftType;
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
