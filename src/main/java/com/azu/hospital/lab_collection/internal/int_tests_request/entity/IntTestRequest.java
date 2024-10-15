package com.azu.hospital.lab_collection.internal.int_tests_request.entity;


import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import com.azu.hospital.lab_collection.internal.int_tests_request.util.IntTestRequestState;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import com.azu.hospital.utils.enums.lab.LabSpots;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.timezone.DateTimeUtility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internal_test_request")
@Getter
@Setter
public class IntTestRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "internal_test_request_id_seq", name = "internal_test_request_id_seq")
    private Long id;

    @Column(name = "test_name")
    private String testName;
    private LabSpots spots;

    @Enumerated(EnumType.STRING)
    private IntTestRequestState state = IntTestRequestState.CREATED;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "internal_lab_test_id")
    private InternalLabTest internalLabTest;

    @OneToMany(mappedBy = "intTestRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IntTestResult> intTestResults;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testNameForMainTest_Id")
    private LabTestMainTableForMainTestName testNameForMainTest;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> testsFiles = new ArrayList<>();

    private Boolean isArchived = false;


    private String note;

    @Column(updatable = false)
    private Instant createdAt;
    private Instant updatedAt;

    public IntTestRequest() {
    }

    public IntTestRequest(LabSpots spots) {
        this.spots = spots;
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
