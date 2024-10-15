package com.azu.hospital.Patients.charts.doctorTour.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;

public record DoctorTourRequest(
        String date,
        String time,
        Double bmi,
        Float weight,
        Float height,
        String medicalDx,
        String tourDetails,
        String followUpNote,
        Boolean isMaskOn,
        DoctorTourShift shift
) {
}
