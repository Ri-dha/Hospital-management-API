package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostOperativeFollowUpCallDtoMapper implements BaseChartDtoMapperInterface {
    private final PatientExperiencingDtoMapper patientExperiencingDtoMapper;

    public PostOperativeFollowUpCallDtoMapper(PatientExperiencingDtoMapper patientExperiencingDtoMapper) {
        this.patientExperiencingDtoMapper = patientExperiencingDtoMapper;
    }
    public PostOperativeFollowUpCallDto chartToDto(PostOperativeFollowUpCall chart){
    return Optional.ofNullable(chart)
            .map(c ->{
                List<PatientExperiencingDto> patientExperiencingDtoList = new ArrayList<>();
                if (c.getPatientExperiencing() != null) {
                    c.getPatientExperiencing().forEach(patientExperiencing ->
                            patientExperiencingDtoList.add(patientExperiencingDtoMapper.entityToDto(patientExperiencing)));
                }

                return new PostOperativeFollowUpCallDto(
                        c.getId(),
                        c.getChartName(),
                        c.getDateOfCall(),
                        c.getLsmCaller(),
                        c.getNumberOfAttempts(),
                        c.getTime(),
                        c.getProcedure(),
                        c.getPainLevel(),
                        c.getLsmPhysicianSignature(),
                        c.getAnesthesiologistSignature(),
                        c.getDateOfService(),
                        patientExperiencingDtoList,
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                );
            })
            .orElseThrow(() -> new ResourceNotFoundException("There Is No Data For Returning"));
    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {

        PostOperativeFollowUpCall newChart = (PostOperativeFollowUpCall) baseCharts;
        if (newChart == null) {
            return null;
        }
        PostOperativeFollowUpCallDto dto = new PostOperativeFollowUpCallDto();
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
        dto.setDateOfCall(newChart.getDateOfCall());
        dto.setLsmCaller(newChart.getLsmCaller());
        dto.setNumberOfAttempts(newChart.getNumberOfAttempts());
        dto.setTime(newChart.getTime());
        dto.setProcedure(newChart.getProcedure());
        dto.setPainLevel(newChart.getPainLevel());
        dto.setLsmPhysicianSignature(newChart.getLsmPhysicianSignature());
        dto.setAnesthesiologistSignature(newChart.getAnesthesiologistSignature());
        dto.setDateOfService(newChart.getDateOfService());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        List<PatientExperiencingDto> patientExperiencingDtoList = new ArrayList<>();
        if (newChart.getPatientExperiencing() != null) {
            newChart.getPatientExperiencing().forEach(patientExperiencing ->
                    patientExperiencingDtoList.add(patientExperiencingDtoMapper.entityToDto(patientExperiencing)));
        }
        dto.setPatientExperiencing(patientExperiencingDtoList);
        return dto;
    }

    @Override
    public Class<PostOperativeFollowUpCall> getMappedClass() {
        return PostOperativeFollowUpCall.class;
    }
}