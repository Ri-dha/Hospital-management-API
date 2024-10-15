package com.azu.hospital.bulding.Shift.arrange_shifts.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.Shift.arrange_shifts.entity.WardsArrangesShiftsTables;
import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.bulding.Shift.shift_timings.entity.GeneralShiftsTiming;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.utils.enums.EnumRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "user_shift_table_main_table")
@Getter
@Setter
public class UserShiftsTable {

    @Id
    @SequenceGenerator(
            name = "user_shift_table_id_sequence",
            sequenceName = "user_shift_table_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_shift_table_generator"
    )
    private Long id;

    @ElementCollection
    private List<Long> shiftId;

    private Long userId;

    private Long wardId;

    private EnumRole role;

    Boolean isArchived = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_id")
    private WardsArrangesShiftsTables wardShifts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BaseUser baseUser;


    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_shift_id")
    private List<CostumeShift> costumeShift;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_shift_id")
    private List<GeneralShiftsTiming> generalShiftsTimings;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wards_id")
    private Ward ward;

    public UserShiftsTable() {
    }


}
