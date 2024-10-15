package com.azu.hospital.Patients.charts.base_chart.dto;

import lombok.Data;

@Data
public class BaseChartsDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;
    private String chartName;



    public BaseChartsDto(Long id, Long patientId, Long doctorId,
                         String doctorName, String patientName,
                         String patientAge, String patientGender,
                         String wardName, String bedNumber, String chartName) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.chartName = chartName;
    }

    public BaseChartsDto() {
    }

    public BaseChartsDto(Long id, Long patientId, Long doctorId, String doctorName, String patientName, String patientAge, String patientGender, String wardName, String bedNumber) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
    }
}
