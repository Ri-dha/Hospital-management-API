package com.azu.hospital.Patients.charts.nursingObservation.entity;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "nursing_observation_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class NursingObservation extends BaseCharts {

    private String chartName = "Nursing Observation Chart";



    @OneToMany(mappedBy = "nursingObservation", cascade = CascadeType.ALL)
    private List<ObservationTime> observationTimes;


    private String note;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id")
    private BaseUser nurse;

    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    public NursingObservation() {
    }


    public NursingObservation(String note) {
        this.note = note;
    }

}
