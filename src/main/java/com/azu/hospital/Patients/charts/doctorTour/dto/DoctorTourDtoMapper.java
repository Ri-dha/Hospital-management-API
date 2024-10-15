package com.azu.hospital.Patients.charts.doctorTour.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorTourDtoMapper implements BaseChartDtoMapperInterface {
    public DoctorTourDto chartToDto(DoctorTourChart chart) {
        return Optional.ofNullable(chart)
                .map(c -> new DoctorTourDto(
                        c.getId(),
                        c.getChartName(),
                        c.getDate(),
                        c.getTime(),
                        c.getBmi(),
                        c.getPatient().getPatientData().getWeight(),
                        c.getPatient().getPatientData().getHeight(),
                        c.getMedicalDx(),
                        c.getTourDetails(),
                        c.getFollowUpNote(),
                        c.getIsMaskOn(),
                        c.getShift(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy(),
                        c.getCreateAt(),
                        c.getUpdateAt()
                )).orElseThrow(
                        () -> new ResourceNotFoundException("No Data For Returning")
                );
    }

    public DtoForReturnIdOnly toDtoId(DoctorTourChart chart) {
        return new DtoForReturnIdOnly(
                chart.getId()
        );
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        DoctorTourChart newChart = (DoctorTourChart) baseCharts;
        if (newChart == null) {
            return null;
        }
        DoctorTourDto dto = new DoctorTourDto();
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
        dto.setDate(newChart.getDate());
        dto.setTime(newChart.getTime());
        dto.setBmi(newChart.getBmi());
        dto.setMedicalDx(newChart.getMedicalDx());
        dto.setTourDetails(newChart.getTourDetails());
        dto.setFollowUpNote(newChart.getFollowUpNote());
        dto.setIsMaskOn(newChart.getIsMaskOn());
        dto.setShift(newChart.getShift());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        dto.setCreatedAt(newChart.getCreateAt());
        dto.setUpdatedAt(newChart.getUpdateAt());
        return dto;
    }

    @Override
    public Class<DoctorTourChart> getMappedClass() {
        return DoctorTourChart.class;
    }
}
