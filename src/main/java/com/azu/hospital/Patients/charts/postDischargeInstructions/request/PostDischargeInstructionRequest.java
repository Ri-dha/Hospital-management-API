package com.azu.hospital.Patients.charts.postDischargeInstructions.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record PostDischargeInstructionRequest(
        String anticoagulantAdvisement,

        String doctorName,

        String phoneNumber,

        String hospitalName,

        String patientSignature,

        String patientSignatureDate,

        String patientSignatureTime,

        String registeredNurseName,

        String registeredDate,

        String registeredTime
) {
}
