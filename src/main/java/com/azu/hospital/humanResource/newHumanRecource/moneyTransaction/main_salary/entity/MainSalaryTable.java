package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.YearMonth;
import java.util.List;

@Entity
@Table(name = "main_salary")
@Setter
@Getter
public class MainSalaryTable {
    @Id
    @SequenceGenerator(
            sequenceName = "main_salary_id_seq" ,
            name = "main_salary_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "main_salary_id_seq"
    )
    private Long mainSalaryId;

    private Double totalSalary;


    @NotNull(message = "Date required")
    private YearMonth yearMonth;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(updatable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "main_salary_custom_salary",
            joinColumns = @JoinColumn(name = "main_salary_id"),
            inverseJoinColumns = @JoinColumn(name = "custom_salary_id")
    )
    private List<CustomSalary> customSalaries;


    private Boolean isArchived = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_user_id")
    private BaseUser users;

    public MainSalaryTable() {
    }

    public MainSalaryTable(Double totalSalary, YearMonth yearMonth, Boolean isArchived) {

        this.totalSalary = totalSalary;
        this.yearMonth = yearMonth;
        this.isArchived = isArchived;
    }

    public MainSalaryTable(Double aDouble, YearMonth yearMonth) {
        this.totalSalary = aDouble;
        this.yearMonth = yearMonth;
    }

    public MainSalaryTable(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }


}
