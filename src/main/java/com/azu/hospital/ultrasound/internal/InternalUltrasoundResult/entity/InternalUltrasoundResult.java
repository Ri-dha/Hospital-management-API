package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity;

import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "internal_ultrasound_results")
@Getter
@Setter
public class InternalUltrasoundResult {

    @Id
    @SequenceGenerator(sequenceName = "internal_ultrasound_result_id" , name = "internal_ultrasound_result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne
    private InternalUltrasoundTest internalUltrasoundTest;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<File> files;

    public InternalUltrasoundResult() {
    }


    public InternalUltrasoundResult(String note) {
        this.note = note;
    }
}
