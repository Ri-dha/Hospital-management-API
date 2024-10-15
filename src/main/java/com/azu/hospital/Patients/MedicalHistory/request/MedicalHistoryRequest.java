package com.azu.hospital.Patients.MedicalHistory.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalHistoryRequest {


    @NotNull
    private Integer patient_id;

    private Integer doctor_id;

    @NotNull
    @NotBlank
    private String chiefComplaint;

    @NotNull
    @NotBlank
    private String historyPresent;

    @NotNull
    @NotBlank
    private String firstDiagnosis;

    private String headNeck;

    private String respiratorySystem;

    private String cardioVascular;

    private String gastroIntestinal;

    private String genitoUrinary;

    private String musculoSkeletal;

    private String nervous;

    private String skin;

    @NotNull
    @NotBlank
    private String pastMedicalHistory;

    private String pastSurgicalHistory;

    private String familyHistory;

    @NotNull
    @NotBlank
    private String drugAllergyHistory;

    private String habits;

    private String sleep;


    private String generalExamination;

    private String height;

    @NotNull
    @NotBlank
    private String weight;

    private String temperature;

    private String pulse;

    private String respRate;

    private String bp;

    private String head;

    private String nose;

    private String ear;

    private String neck;

    private String thorax;

    private String breasts;

    private String lungs;

    private String heart;

    private String abdomen;

    private String liver;

    private String spleen;

    private String kidneys;

    private String inguinalRegion;

    private String genitalia;



    private String rectalExamination;

    private String extremitiesJoints;

    private String hairNails;

    private String lymphGlands;


    private String neurologicalExamination;

    private String investigation;

    private String provisionalDiagnosis;

    @NotNull
    @NotBlank
    private String treatment;

    private String diet;

}
