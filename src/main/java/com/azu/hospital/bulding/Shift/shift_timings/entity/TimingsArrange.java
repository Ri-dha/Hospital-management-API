package com.azu.hospital.bulding.Shift.shift_timings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;


@Entity
@Table(name = "timing_arrange")
@Getter
@Setter
public class TimingsArrange {

    @Id
    @SequenceGenerator(
            name = "timing_arrange_id_seq",
            sequenceName = "timing_arrange_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "timing_arrange_id_seq"
    )

    private Long id;

    private Time startFrom;

    private Time toTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "costume_id")
    private CostumeShift costumeShift;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "genralId_id")
    private GeneralShiftsTiming generalShiftsTiming;


    public TimingsArrange() {
    }

    public TimingsArrange(Time startFrom, Time toTime) {
        this.startFrom = startFrom;
        this.toTime = toTime;
    }
}
