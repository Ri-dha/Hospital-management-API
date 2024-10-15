package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;

public record BabyDoctorTourCreateRequest(

        String note,

        String medicalDx,
        String tourDetails,
        String followUpNote
) {
}
