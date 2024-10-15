package com.azu.hospital.Patients.charts.preMedicalAssessment.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreMedicalAssessmentDtoMapper  {
  public PreMedicalAssessmentDto chartToDto(PreMedicalAssessmentChart chart){
    return Optional.ofNullable(chart)
            .map(c -> new PreMedicalAssessmentDto(
                    c.getId(),
                    c.getChartName(),
                    c.getPlannedSurgicalProcedure(),
                    c.getSurgeryDate(),
                    c.getSurgeryLocation(),
                    c.getAttendingSurgeon(),
                    c.getPresentIllnessHistory(),
                    c.getPastMedicalHistoryNote(),
                    c.getPastMedicalHistoryTable(),
                    c.getPriorAnesthesiaComplication(),
                    c.getSocialHistory(),
                    c.getCardiacHistory(),
                    c.getSymptomsSystem(),
                    c.getBmi(),
                    c.getNormalExamCheck(),
                    c.getSurgicalRisk(),
                    c.getCardiacRiskAssessment(),
                    c.getCreatedBy(),
                    c.getLastModifiedBy()
//                    c.getPatient().lastMedicalHistory().getDrugHistoryAllergy()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There is No Data For Returning"
                    )
            );
  }

//    @Override
//    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
////        PreMedicalAssessmentChart newChart = (PreMedicalAssessmentChart) baseCharts;
//        PreMedicalAssessmentChart newChart = new PreMedicalAssessmentChart();
//        PreMedicalAssessmentDto dto = new PreMedicalAssessmentDto();
//
//        dto.setId(newChart.getId());
//        dto.setChartName(newChart.getChartName());
//        dto.setPlannedSurgicalProcedure(newChart.getPlannedSurgicalProcedure());
//        dto.setSurgeryDate(newChart.getSurgeryDate());
//        dto.setSurgeryLocation(newChart.getSurgeryLocation());
//        dto.setAttendingSurgeon(newChart.getAttendingSurgeon());
//        dto.setPresentIllnessHistory(newChart.getPresentIllnessHistory());
//        dto.setPastMedicalHistoryNote(newChart.getPastMedicalHistoryNote());
//        dto.setPastMedicalHistoryTable(newChart.getPastMedicalHistoryTable());
//        dto.setPriorAnesthesiaComplication(newChart.getPriorAnesthesiaComplication());
//        dto.setSocialHistory(newChart.getSocialHistory());
//        dto.setCardiacHistory(newChart.getCardiacHistory());
//        dto.setSymptomsSystem(newChart.getSymptomsSystem());
//        dto.setBmi(newChart.getBmi());
//        dto.setNormalExamCheck(newChart.getNormalExamCheck());
//        dto.setSurgicalRisk(newChart.getSurgicalRisk());
//        dto.setCardiacRiskAssessment(newChart.getCardiacRiskAssessment());
//
//        return dto;
//    }
}
