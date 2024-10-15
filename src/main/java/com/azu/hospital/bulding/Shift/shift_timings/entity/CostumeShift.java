package com.azu.hospital.bulding.Shift.shift_timings.entity;

import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
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
@Table(name = "costume_shift")
@Getter
@Setter
public class CostumeShift {

    @Id
    @SequenceGenerator(
            name = "costume_shift_id_seq",
            sequenceName = "costume_shift_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "costume_shift_id_seq"
    )

    private Long costumeId;

    @Enumerated(EnumType.STRING)
    private EnumRole role;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "costumeShift")
    private List<TimingsArrange> costumeListTim;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "costume_id")
    private ShiftTiming shiftTiming;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserShiftsTable> userShiftsTablesCostumes;


    public CostumeShift() {
    }

    public CostumeShift(EnumRole role) {
        this.role = role;

    }
}
