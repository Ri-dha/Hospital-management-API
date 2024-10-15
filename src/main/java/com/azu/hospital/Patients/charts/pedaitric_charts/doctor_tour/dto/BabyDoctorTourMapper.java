package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dto;

import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDtoMapper;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity.BabyDoctorTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BabyDoctorTourMapper implements Function<BabyDoctorTour, BabyDoctorTourDto> {

    private final PrematureBabyDtoMapper prematureBabyMapper;

    @Autowired
    public BabyDoctorTourMapper(PrematureBabyDtoMapper prematureBabyMapper) {
        this.prematureBabyMapper = prematureBabyMapper;
    }


    @Override
    public BabyDoctorTourDto apply(BabyDoctorTour babyDoctorTour) {
        return new BabyDoctorTourDto(
                babyDoctorTour.getId(),
                babyDoctorTour.getNote(),
                babyDoctorTour.getMedicalDx(),
                babyDoctorTour.getTourDetails(),
                babyDoctorTour.getFollowUpNote(),
                babyDoctorTour.getId(),
                babyDoctorTour.getPrematureBaby().getName(),
                babyDoctorTour.getPrematureBaby().getPatient().getId(),
                babyDoctorTour.getPrematureBaby().getPatient().getPatientData().getFullName(),
                babyDoctorTour.getCreatedBy(),
                babyDoctorTour.getLastModifiedBy(),
                babyDoctorTour.getCreatedAt(),
                babyDoctorTour.getUpdateAt()

        );
    }
}
