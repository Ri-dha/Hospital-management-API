package com.azu.hospital.lab_collection.lab_test_main_table.entity;

import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "main_lab_table_list")
@Getter
@Setter
public class LabTestMainTableForMainTestName {
    @Id
    @SequenceGenerator(
            name = "main_lab_table_for_main_test_list_id_seq",
            sequenceName = "main_lab_table_for_main_test_list_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "main_lab_table_for_main_test_list"
    )
    private Long testId;

    @NotNull(message = "Test Name Required")
    @NotEmpty(message = "Test Name Required")
    @NotBlank(message = "Test Name Required")
    @JsonProperty("name")
    private String testName;

    @Column(columnDefinition = "TEXT")
    private String normalValue;

    private String Note;


    private BigDecimal price=BigDecimal.ZERO;

    public LabTestMainTableForMainTestName() {
    }

    public LabTestMainTableForMainTestName(Long testId, String testName) {
        this.testId = testId;
        this.testName = testName;
    }

    public LabTestMainTableForMainTestName(String testName) {
        this.testName = testName;
    }
}
