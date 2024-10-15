package com.azu.hospital.Patients.charts.postAnestheticEvaluation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostAnestheticEvaluationDtoMapper implements BaseChartDtoMapperInterface {

  public PostAnestheticEvaluationDto anestheticEvaluationChartToDto(
          PostAnestheticEvaluationChart chart
  ) {
    return Optional.ofNullable(chart)
            .map(c -> new PostAnestheticEvaluationDto(
                    c.getId(),
                    c.getChartName(),
                    c.getAnesthesiaComplications(),
                    c.getPatientStatus(),
                    c.getPatientHas(),
                    c.getPatientHasOther(),
                    c.getPostAnesthesiaNote(),
                    c.getAnesthesiologist(),
                    c.getDate(),
                    c.getTime(),
                    c.getCreatedBy(),
                    c.getLastModifiedBy()
            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There is no data for return"
                    )
            );
  }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        PostAnestheticEvaluationChart newChart = (PostAnestheticEvaluationChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        PostAnestheticEvaluationDto dto = new PostAnestheticEvaluationDto();
        dto.setId(newChart.getId());
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setAnesthesiaComplications(newChart.getAnesthesiaComplications());
        dto.setPatientStatus(newChart.getPatientStatus());
        dto.setPatientHas(newChart.getPatientHas());
        dto.setPatientHasOther(newChart.getPatientHasOther());
        dto.setPostAnesthesiaNote(newChart.getPostAnesthesiaNote());
        dto.setAnesthesiologist(newChart.getAnesthesiologist());
        dto.setDate(newChart.getDate());
        dto.setTime(newChart.getTime());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<PostAnestheticEvaluationChart> getMappedClass() {
        return PostAnestheticEvaluationChart.class;
    }
}
