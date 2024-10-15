package com.azu.hospital.Patients.charts.operativeNote.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperativeNoteDtoMapper implements BaseChartDtoMapperInterface {

    public OperativeNoteDto chartDto(OperativeNote operativeNote) {
        return Optional.ofNullable(operativeNote)
                .map(c -> new OperativeNoteDto(
                      c.getId(),
                        c.getChartName(),
                        c.getOperativeNote(),
                        c.getOperationName(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()

                ))
                .orElseThrow(
                        () -> new RuntimeException(
                                "There is not data for returning"
                        ))
                ;
    }
    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
      OperativeNote newChart = (OperativeNote) baseCharts;
      if(newChart == null){
        return null;
      }
        OperativeNoteDto dto = new OperativeNoteDto();
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setOperativeNote(newChart.getOperativeNote());
        dto.setOperationName(newChart.getOperationName());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());

        return dto;
    }

    @Override
    public Class<OperativeNote> getMappedClass() {
        return OperativeNote.class;
    }
}
