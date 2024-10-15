package com.azu.hospital.lab_collection.external.ext_tests_request.entity;


import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;
import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import com.azu.hospital.lab_collection.test_list.entity.LabList;
import com.azu.hospital.utils.enums.lab.LabSpots;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "external_test_request")
public class ExtTestRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "external_test_request_id_seq", name = "external_test_request_id_seq")
    private Long id;
    private String testName;
    private LabSpots spots;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_test_id")
    private LabList labList;  // the main list of the test

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "external_lab_test_id")
    private ExternalLabTest externalLabTest; // patient data

    private String note;

    @OneToMany(mappedBy = "extTestRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExtTestResult> extTestRequest;

    public ExtTestRequest(String testName, LabSpots spots, String note) {
        this.testName = testName;
        this.spots = spots;
        this.note = note;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(updatable = false)
    private Instant createdAt;
    private Instant updatedAt;

    public ExtTestRequest() {
    }

    public ExtTestRequest(LabSpots spots) {
        this.spots = spots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LabSpots getSpots() {
        return spots;
    }

    public void setSpots(LabSpots spots) {
        this.spots = spots;
    }


    public LabList getLabList() {
        return labList;
    }

    public void setLabList(LabList labList) {
        this.labList = labList;
    }

    public ExternalLabTest test() {
        return this.externalLabTest;
    }

    public void setExternalLabTest(ExternalLabTest externalLabTest) {
        this.externalLabTest = externalLabTest;
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
