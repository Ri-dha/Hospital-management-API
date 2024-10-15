package com.azu.hospital.operation.dto;

import com.azu.hospital.utils.enums.AnesthesiaType;
import com.azu.hospital.utils.enums.Gender;
import com.azu.hospital.utils.enums.operation.OperationAnesthetization;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.operation.OperationTriage;
import com.azu.hospital.utils.enums.operation.OperationTypes;
import com.azu.hospital.utils.enums.patient.PatientTriageEnum;

import java.time.Instant;
import java.util.List;

public record OperationDto(

        Long id,
        Long patientId,
        String requestNote,
        String RecoverNote,
        String patientName,
        String doctorName,
        Long uploaderId,
        String uploaderName,
        List<String> uploaderRole,
        Long accepterId,
        String accepterName,
        List<String> accepterRole,
        Long rejecterId,
        String rejecterName,
        List<String> rejecterRole,
        String mobile,
        Instant date,
        Long bedId,
        String bedNumber,
        Gender gender,
        String age,
        Float patientWeight,
        Float patientHeight,
        Long wardId ,
        String wardName,
        String dx,
        String allergy,
        String surgicalName,
        AnesthesiaType anesthetization ,
        PatientTriageEnum patientTriage ,
        OperationTriage operationTriage,
        OperationStates operationStates,
        Instant createdAt,
        Instant updatedAt,
        Instant operationDate
) {
}
