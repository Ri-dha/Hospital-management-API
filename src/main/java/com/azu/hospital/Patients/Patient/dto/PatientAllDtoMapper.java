package com.azu.hospital.Patients.Patient.dto;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.services.QrCodeService;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientAllDtoMapper implements Function<Patient, PatientAllDto> {
    private final QrCodeService qrCodeService;

    public PatientAllDtoMapper(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @Override
    public PatientAllDto apply(Patient patient) {
        String dischargedDate = null;
        if (patient.getBaseCharts() != null) {
            for (BaseCharts chart : patient.getBaseCharts()) {
                if (chart instanceof DischargeChart) {
                    DischargeChart dischargeChart = (DischargeChart) chart;
                    if (dischargeChart.getDischargedDate() != null) {
                        dischargedDate = dischargeChart.getDischargedDate().toString();
                        break;
                    }
                }
            }
        }

        String appernt = null;
        if (appernt != null){
            appernt = patient.getPatientMedicalInfo().getApparentImpairments();
        }

        Integer timeE = null;
        if (timeE != null){
            timeE = patient.getPatientMedicalInfo().getTimeOfEntries();
        }

        String referF = null;
        if (referF != null){
            referF = patient.getPatientMedicalInfo().getReferredFrom();
        }
        String transF = null;
        if (transF != null){
            transF = patient.getPatientMedicalInfo().getTransformations();
        }
        return new PatientAllDto(
                patient.getId(),
                patient.getJobInfo().getJob(),
                patient.getJobInfo().getJobType(),
                patient.getPatientAddress().getBirthAddress(),
                patient.getPatientAddress().getAddress(),
                patient.getPatientContact().getEmail(),
                patient.getPatientContact().getMobile(),
                patient.getPatientContact().getRelativeMobile(),
                patient.getPatientData().getFullName(),
                patient.getPatientData().getMotherName(),
                patient.getPatientData().getGender(),
                patient.getPatientData().getWeight(),
                patient.getPatientData().getHeight(),
                patient.getPatientData().getCertification(),
                patient.getPatientData().getLiveEnum(),
                patient.getPatientData().getSocialState(),
                patient.getPatientData().getOperation(),
                patient.getPatientDate().getBirthDate(),
                patient.getPatientDate().getAdmissionDate(),
                patient.getPatientDate().getAge(),
                appernt,
                timeE,
                referF,
                transF,
                patient.getDoctor().get(0).getDoctor().getId(),
                patient.getDoctor().get(0).getDoctor().getUsernameSpecial(),
                patient.getPermanentDoctor() == null ? null : patient.getPermanentDoctor().getUsernameSpecial(),
                patient.getImages(),
                patient.getFiles(),
                patient.getImage(),
                patient.getWard() == null ? null : patient.getWard().getName(),
                patient.getBed() == null ? null : patient.getBed().getBedNumber(),
                patient.getCreatedAt(),
                qrCodeService.getQrCodeUrl(patient.getId()),
                patient.getIsHasMedicalHistory(),
                patient.lastMedicalHistory() == null ? null : patient.lastMedicalHistory().getTriage(),
                patient.getBillState(),
                dischargedDate,
                patient.getEntryNo(),
                patient.getWard() == null ? null : patient.getWard().getFloor().getFloorName(),
                patient.getTests()
        );
    }
}
