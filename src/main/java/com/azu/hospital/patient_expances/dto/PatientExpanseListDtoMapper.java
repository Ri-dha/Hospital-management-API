package com.azu.hospital.patient_expances.dto;

import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
@Service
public class PatientExpanseListDtoMapper implements Function<PatientExpanseList, PatientExpanseListDto> {

    private final PatientExpanseDtoMapper patientExpanseDtoMapper;

    @Autowired
    public PatientExpanseListDtoMapper(PatientExpanseDtoMapper patientExpanseDtoMapper) {
        this.patientExpanseDtoMapper = patientExpanseDtoMapper;
    }

    @Override
    public PatientExpanseListDto apply(PatientExpanseList patientExpanseList) {
        List<PatientExpanseDto> patientExpanseDtoList = new ArrayList<>();
        for (PatientExpanse patientExpanse : patientExpanseList.getPatientExpanse()) {
            patientExpanseDtoList.add(patientExpanseDtoMapper.apply(patientExpanse));
        }

        return new PatientExpanseListDto(
                patientExpanseList.getId(),
                patientExpanseList.getPatient().getId(),
                patientExpanseList.getPatient().getPatientData().getFullName(),
                patientExpanseList.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial(),
                patientExpanseList.getPatient().getDoctorSpecials().getDoctor().getId(),
                patientExpanseList.getPatient().getWard().getName(),
                patientExpanseList.getPatient().getBed().getBedNumber(),
                patientExpanseList.getPatient().getPatientData().getGender(),
                patientExpanseList.getPatient().getPatientDate().getAge(),
                patientExpanseList.getUser().getUsername(),
                patientExpanseList.getUser().getId(),
                patientExpanseDtoList,
                patientExpanseList.getCreatedAt(),
                patientExpanseList.getUpdateAt() == null ? null: patientExpanseList.getCreatedAt()
        );
    }
}
