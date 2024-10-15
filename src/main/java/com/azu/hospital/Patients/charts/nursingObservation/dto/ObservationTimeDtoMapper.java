package com.azu.hospital.Patients.charts.nursingObservation.dto;

import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ObservationTimeDtoMapper implements Function<ObservationTime, ObservationTimeDto>{


    @Override
    public ObservationTimeDto apply(ObservationTime observationTime) {
        return new ObservationTimeDto(
                observationTime.getId(),
                observationTime.getTime(),
                observationTime.getTimeStatus(),
                observationTime.getDrugs().getType(),
                observationTime.getDrugs().getDoes(),
                observationTime.getRoa(),
                observationTime.getDrugs().getDrugName()
        );
    }
}
