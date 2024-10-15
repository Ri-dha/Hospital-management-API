package com.azu.hospital.Patients.charts.nursingObservation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.utils.enums.EnumRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NursingObservationDto extends BaseChartsDto {
    private Long nursingChartId;
    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Nursing Assessment Chart";

    private Long nursingObservationId;

    private Long nurseId;

    private String nurseName;

    private String role;

    private String image;


    private String note;

    List<ObservationTimeDto> observationTimes;

    private Instant createdAt;

    private Long createdBy;
    private Long LastModifiedBy;
    private BaseUserDto createdByUser;
    private BaseUserDto LastModifiedByUser;

    public NursingObservationDto(Long nursingObservationId,
                                 Long nurseId,
                                 String nurseName,
                                 String role,
                                 String image,
                                 String chartName,
                                 String note,
                                 List<ObservationTimeDto> observationTimes,
                                 Instant createdAt,
                                 Long createdBy,
                                 Long LastModifiedBy,
                                    BaseUserDto createdByUser,
                                    BaseUserDto LastModifiedByUser
    ) {
        this.nursingObservationId = nursingObservationId;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.role = role;
        this.image = image;
        this.chartName = chartName;
        this.note = note;
        this.observationTimes = observationTimes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
        this.createdByUser = createdByUser;
        this.LastModifiedByUser = LastModifiedByUser;
    }


    public NursingObservationDto() {

    }

}
