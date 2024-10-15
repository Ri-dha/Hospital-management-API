    package com.azu.hospital.lab_collection.internal.int_test_result.entity;

    import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
    import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
    import com.azu.hospital.utils.files.File;
    import com.azu.hospital.utils.timezone.DateTimeUtility;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.Instant;
    import java.time.ZoneId;
    import java.time.ZonedDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "internal_test_results")
    @Getter
    @Setter
    public class IntTestResult {

        @Id
        @SequenceGenerator(name = "int_test_result_seq", sequenceName = "int_test_result_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        private String resultName;

        private String testResult;

        private String normalValue;

        @Column(updatable = false)
        private Instant createdAt;

        @Column(insertable = false)
        private Instant updatedAt;


        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "int_test_request_id")
        private IntTestRequest intTestRequest;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<File> files = new ArrayList<>();



        public IntTestResult() {
        }


        public IntTestResult(String resultName, String testResult, String normalValue) {
            this.resultName = resultName;
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
