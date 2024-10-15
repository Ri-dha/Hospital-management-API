package com.azu.hospital.Patients.charts.base_chart.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "base_chart")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "entity_type",
        discriminatorType
                = DiscriminatorType.STRING)
public abstract class BaseCharts implements BaseChartInterface {
    @Id
    @SequenceGenerator(
            sequenceName = "base_chart_id_sequence",
            name = "base_chart_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "base_chart_id_sequence"
    )
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_id")
    private List<BaseUser> baseUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    private String chartName;

    public BaseCharts() {

    }

    public BaseCharts(Long id, Patient patient, Instant createdAt, Instant updatedAt, String chartName) {
        this.id = id;
        this.patient = patient;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chartName = chartName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Patient getPatient() {
        return patient;
    }

    @Override
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void setBaseUser(List<BaseUser> baseUser) {
        this.baseUser = baseUser;
    }

    @Override
    public BaseUser getBaseUser() {
        return baseUser
                .stream()
                .findFirst()
                .orElse(null);
    }


    public Instant getCreateAt() {
        return this.createdAt;
    }

    public Instant getUpdateAt() {
        return this.updatedAt;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
}
