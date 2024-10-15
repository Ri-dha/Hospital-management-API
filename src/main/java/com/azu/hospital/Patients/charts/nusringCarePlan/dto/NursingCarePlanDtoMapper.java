package com.azu.hospital.Patients.charts.nusringCarePlan.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NursingCarePlanDtoMapper implements BaseChartDtoMapperInterface
{
  public NursingCarePlanDto chartDto(NursingCarePlan nursingCarePlan) {
    return Optional.ofNullable(nursingCarePlan)
            .map(c -> new NursingCarePlanDto(
                    c.getId(),
                    c.getChartName(),
                    c.getNursingDiagnosis(),
                    c.getGoalsAndOutComes(),
                    c.getIntervensions(),
                    c.getEvaluation(),
                    c.getCreatedBy(),
                    c.getLastModifiedBy()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There is not data for returning"
                    )
            );
  }
  @Override
  public BaseChartsDto mapToDto(BaseCharts baseCharts) {
      NursingCarePlan newChart = (NursingCarePlan) baseCharts;
        if (newChart == null) {
            return null;
        }
        NursingCarePlanDto dto = new NursingCarePlanDto();
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setNursingDiagnosis(newChart.getNursingDiagnosis());
        dto.setGoalsAndOutComes(newChart.getGoalsAndOutComes());
        dto.setIntervensions(newChart.getIntervensions());
        dto.setEvaluation(newChart.getEvaluation());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;

  }

    @Override
    public Class<NursingCarePlan> getMappedClass() {
        return NursingCarePlan.class;
    }


}
