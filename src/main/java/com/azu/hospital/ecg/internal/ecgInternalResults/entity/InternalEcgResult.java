package com.azu.hospital.ecg.internal.ecgInternalResults.entity;


import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internal_ecg_results")
@Getter
@Setter
public class InternalEcgResult {

    @Id
    @SequenceGenerator(
            sequenceName = "internal_ecg_result_id",
            name = "internal_ecg_result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne
    private Ecg ecg;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

    public InternalEcgResult() {
    }

    public InternalEcgResult(String note) {
        this.note = note;
    }
}
