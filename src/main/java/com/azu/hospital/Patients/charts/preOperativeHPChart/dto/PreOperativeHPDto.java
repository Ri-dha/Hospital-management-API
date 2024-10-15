package com.azu.hospital.Patients.charts.preOperativeHPChart.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.HealthCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCondition;
import com.azu.hospital.Patients.charts.physical_assment.Habit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PreOperativeHPDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Pre Operative HP Chart";
    private String diagnosisChiefComplaint;
    private String pastMedicalHistory;
    private String familyPhysician;
    private String surgicalHistory;
    private String medications;
    private String allergy;
    private Map<Habit, Boolean> hobbies;
    private Map<MedicalCondition, Boolean> chronicDisease;
    private Map<HealthCondition, Boolean> familyHistory;
    private String general;
    private String headNeck;
    private String chest;
    private String heart;
    private String lungs;
    private String abdomen;
    private String skinOfExtremities;
    private String impression;

    private Long createdBy;
    private Long LastModifiedBy;


    private String title = "PRE OPERATIVE H&P";
    private String link = "operative";

    public PreOperativeHPDto(
            Long id,
            String chartName,
            String diagnosisChiefComplaint,
            String pastMedicalHistory,
            String familyPhysician,
            String surgicalHistory,
            String medications,
            Map<Habit, Boolean> hobbies,
            Map<MedicalCondition, Boolean> chronicDisease,
            Map<HealthCondition, Boolean> familyHistory,
            String general,
            String headNeck,
            String chest,
            String heart,
            String lungs,
            String abdomen,
            String skinOfExtremities,
            String impression,
            String allergy,
            Long createdBy,
            Long lastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.diagnosisChiefComplaint = diagnosisChiefComplaint;
        this.pastMedicalHistory = pastMedicalHistory;
        this.familyPhysician = familyPhysician;
        this.surgicalHistory = surgicalHistory;
        this.medications = medications;
        this.hobbies = hobbies;
        this.chronicDisease = chronicDisease;
        this.familyHistory = familyHistory;
        this.general = general;
        this.headNeck = headNeck;
        this.chest = chest;
        this.heart = heart;
        this.lungs = lungs;
        this.abdomen = abdomen;
        this.skinOfExtremities = skinOfExtremities;
        this.impression = impression;
        this.allergy = allergy;
        this.createdBy = createdBy;
        LastModifiedBy = lastModifiedBy;
    }

    public PreOperativeHPDto() {

    }
}
