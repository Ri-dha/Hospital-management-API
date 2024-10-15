package com.azu.hospital.Patients.charts.burnDiagram.request;


import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnBodyDiagram;

import java.time.LocalDate;

public record BurnDiagramRequest(

        LocalDate dateOfBurn,
        String dateOfAdmission,
        String dateOfTbsaEstimationPreliminary,
        String preliminaryDoctorName,
        String dateOfTbsaEstimationFinal,
        String finalDoctorName,
        Float superficialDermal,
        Float midDermal,
        Float deepDermal,
        Float midDermalFluidLoos,
        Float deepDermalFluidLoss,
        Float finalTbsa,
        Float totalFluidLoos,
        BurnBodyDiagram burnBodyDiagram
) {
}
