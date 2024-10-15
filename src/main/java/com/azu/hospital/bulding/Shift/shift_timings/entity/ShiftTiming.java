package com.azu.hospital.bulding.Shift.shift_timings.entity;

import com.azu.hospital.utils.enums.EnumRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shift_timing")
@Data
public class ShiftTiming {
    @Id
    @SequenceGenerator(
            name = "shift_timing_id_seq",
            sequenceName = "shift_timing_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shifts_id_seq"
    )

    private Long timingId;

    @Enumerated(EnumType.STRING)
    private EnumRole role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shiftTiming")
    private GeneralShiftsTiming generalShiftsTimings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shiftTiming")
    private CostumeShift costumeShift;
}
