package com.azu.hospital.bulding.Shift.shift_timings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "general_shift_timing")
@Getter
@Setter
public class GeneralShiftsTiming {

    @Id
    @SequenceGenerator(
            name = "general_shift_timing_id_seq",
            sequenceName = "general_shift_timing_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "general_shift_timing_id_seq"
    )

    private Long generalId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "generalShiftsTiming")
    private List<TimingsArrange> timingsArranges;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "timing_id")
    private ShiftTiming shiftTiming;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public GeneralShiftsTiming() {
    }


}
