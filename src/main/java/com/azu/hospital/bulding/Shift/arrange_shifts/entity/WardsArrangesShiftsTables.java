package com.azu.hospital.bulding.Shift.arrange_shifts.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import com.azu.hospital.bulding.ward.entity.Ward;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "wards_arranges_shifts_Tables")
@Getter
@Setter
public class WardsArrangesShiftsTables {

    @Id
    @SequenceGenerator(
            name = "wards_arranges_shifts_Tables_id_sequence",
            sequenceName = "wards_arranges_shifts_Tables_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wards_arranges_shifts_Tables_id_seq"
    )
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "wardShifts")
    private List<UserShiftsTable> userShiftTable;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_shift_id")
    private List<CostumeShift> costumeShift;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_shift_id")
    private List<GeneralShiftsTiming> generalShiftsTimings;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_user_id")
    private List<BaseUser> users;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public WardsArrangesShiftsTables() {
    }
}
