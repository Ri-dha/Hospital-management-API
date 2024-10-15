package com.azu.hospital.lab_collection.external.ext_test_result.entity;

import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "external_test_results")
@Getter
@Setter
public class ExtTestResult {

    @Id
    @SequenceGenerator(name = "external_test_results_id_seq", sequenceName = "external_test_results_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String resultName;


    private String testResult;

    private String normalValue;


    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ext_test_request_id")
    private ExtTestRequest extTestRequest;

    public ExtTestResult() {
    }

    public ExtTestResult(String testName, String testResult, String normalValue) {
        this.resultName = testName;
        this.testResult = testResult;
        this.normalValue = normalValue;

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
