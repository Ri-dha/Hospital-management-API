package com.azu.hospital.Patients.Patient.dto;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.services.QrCodeService;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PatientDtoMapper  {

    private final QrCodeService qrCodeService;
    String dischargedDate = null;

    public PatientDtoMapper(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }


    public PatientDto toDto(Patient patient){
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

            return new PatientDto(
                    patient.getId(),
                    patient.getJobInfo(),
                    patient.getPatientAddress(),
                    patient.getPatientContact(),
                    patient.getPatientData(),
                    patient.getPatientDate() ,
                    patient.getPatientMedicalInfo() ,
                    patient.getDoctor().get(0).getDoctor().getId() ,
                    patient.getDoctor().get(0).getDoctor().getUsernameSpecial() ,
                    patient.getPermanentDoctor() ==null ? null : patient.getPermanentDoctor().getUsernameSpecial() ,
                    patient.getImages() ,
                    patient.getFiles() ,
                    patient.getImage(),
                    patient.getCreatedAt(),
                    patient.getWard() == null ? null : patient.getWard().getName(),
                    patient.getBed() == null ? null : patient.getBed().getBedNumber(),
                    qrCodeService.getQrCodeUrl(patient.getId()),
                    patient.getIsHasMedicalHistory(),
                    patient.lastMedicalHistory() == null ? null : patient.lastMedicalHistory().getTriage(),
                    patient.getBillState(),
                    dischargedDate,
                    patient.getEntryNo(),
                    patient.getWard() == null ? null : patient.getWard().getFloor().getFloorName(),
                    patient.getTests(),
                    patient.getCreatedBy(),
                    patient.getLastModifiedBy(),
                    patient.getIsArchived()


            ) ;

    }

}
