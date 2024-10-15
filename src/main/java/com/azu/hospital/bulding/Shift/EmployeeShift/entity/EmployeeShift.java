package com.azu.hospital.bulding.Shift.EmployeeShift.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.Shift.entity.Shift;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "employee_shifts")
@Data
@NoArgsConstructor()
public class EmployeeShift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_shifts_id_seq", sequenceName = "employee_shifts_id_seq")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private BaseUser user;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ElementCollection
    @Size(min = 1, max = 31)
    private List<Integer> shiftingDays;

    private Integer day;


    private Instant createdAt;

    private Instant updatedAt;

    public EmployeeShift(BaseUser user, Shift shift, Ward ward ) {
        this.user = user;
        this.shift = shift;
        this.ward = ward;

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
