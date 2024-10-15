package com.azu.hospital.Patients.charts.nursingObservation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.nursingObservation.entity.NursingObservation;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class NursingObservationDtoMapper implements BaseChartDtoMapperInterface {

    private final ObservationTimeDtoMapper observationTimeDtoMapper;
    private final BaseUserDao baseUserDao;
    private final BaseUserDtoMapper baseUserDtoMapper;

    @Autowired
    public NursingObservationDtoMapper(
            @Qualifier("observationTimeDtoMapper") ObservationTimeDtoMapper observationTimeDtoMapper, BaseUserDao baseUserDao, BaseUserDtoMapper baseUserDtoMapper) {
        this.observationTimeDtoMapper = observationTimeDtoMapper;

        this.baseUserDao = baseUserDao;
        this.baseUserDtoMapper = baseUserDtoMapper;
    }

    public NursingObservationDto chartToDto(NursingObservation chart) {
        return Optional.ofNullable(chart)
                .map(c->{
                    List<ObservationTimeDto> observationTimeDtoList = new ArrayList<>();
                    if (c.getObservationTimes() != null) {
                        c.getObservationTimes().forEach(observationTime ->
                                observationTimeDtoList.add(observationTimeDtoMapper.apply(observationTime)));
                    }
                    String image = null;
                    if (c.getNurse() instanceof Nurse) {
                        image = ((Nurse) c.getNurse()).getImage();

                    } else if (c.getNurse() instanceof Doctor) {
                        image = ((Doctor) c.getNurse()).getImage();

                    }

                    long userCreateId;
                    long userModifiedId;
                    if (c.getCreatedBy() != null) {
                        userCreateId = c.getCreatedBy();
                    } else {
                        userCreateId = 0;
                    }
                    if (c.getLastModifiedBy() != null) {
                        userModifiedId = c.getLastModifiedBy();
                    } else {
                        userModifiedId = 0;
                    }
                    BaseUserDto userId = null;
                    BaseUserDto userIdModified = null;
                    if (c.getCreatedBy() != null) {
                        userId =  getUser(c.getCreatedBy());
                    }
                    if (c.getLastModifiedBy() != null) {
                        userIdModified =  getUser(c.getLastModifiedBy());
                    }

                    return new NursingObservationDto(
                            c.getId(),
                            c.getNurse().getId(),
                            c.getNurse().getUsername(),
                            c.getNurse().getRoles().toString(),
                            image,
                            c.getChartName(),
                            c.getNote(),
                            observationTimeDtoList,
                            c.getCreateAt(),
                            userCreateId,
                            userModifiedId,
                            userId,
                            userIdModified

                    );
                }).orElseThrow(
                        () -> new ResourceNotFoundException("There Is No Data For Returning")
                );

    }



    private BaseUserDto getUser(long id) {
        return baseUserDao.findById(id)
                .map(baseUserDtoMapper)
                .orElse(null);
    }


    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        NursingObservation newChart = (NursingObservation) baseCharts;
        if (newChart == null) {
            return null;
        }
        NursingObservationDto dto = new NursingObservationDto();
        dto.setNursingChartId(newChart.getId());
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setNote(newChart.getNote());
        dto.setNurseId(newChart.getNurse().getId());
        dto.setNurseName(newChart.getNurse().getUsername());
        dto.setRole(newChart.getNurse().getRoles().toString());
        BaseUser nurseOrDoctor = newChart.getNurse();
        String image = "";
        if (nurseOrDoctor instanceof Nurse) {
            image = ((Nurse) nurseOrDoctor).getImage();
        } else if (nurseOrDoctor instanceof Doctor) {
            image = ((Doctor) nurseOrDoctor).getImage();
        }
        dto.setImage(image);
        dto.setCreatedAt(newChart.getCreateAt());
        List<ObservationTimeDto> observationTimeDtoList = new ArrayList<>();
        if (newChart.getObservationTimes() != null) {
            newChart.getObservationTimes().forEach(observationTime ->
                    observationTimeDtoList.add(observationTimeDtoMapper.apply(observationTime)));
        }
        dto.setObservationTimes(observationTimeDtoList);
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;

    }

    @Override
    public Class<?> getMappedClass() {
        return NursingObservation.class;
    }
}
