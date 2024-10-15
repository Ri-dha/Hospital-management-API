package com.azu.hospital.Patients.charts.nursingObservation.entity;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.utils.enums.AdministrationRoute;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "observation_time")
@Getter
@Setter
public class ObservationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nursing_observation_id")
    private NursingObservation nursingObservation;

    //    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time format should be like HH:MM")
    @NotNull(message = "Time is Required")
    @NotBlank(message = "Time is Required")
    @NotEmpty(message = "Time is Required")
    private String time;


    private String timeStatus;

    private String roa;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drugs_request_id")
    private DrugRequestHandler drugs;


    public ObservationTime() {
    }

    public ObservationTime(String time, String timeStatus, String roa) {
        this.time = time;
        this.timeStatus = timeStatus;
        this.roa = roa;
    }

}