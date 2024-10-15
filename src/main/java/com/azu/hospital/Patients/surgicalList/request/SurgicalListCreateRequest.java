package com.azu.hospital.Patients.surgicalList.request;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.patient.AnesthesiaEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SurgicalListCreateRequest {

    @NotNull(message = "Patient id required")
    private Long patientId;

    @NotNull(message = "Surgical name field required")
    private String surgicalName;

    @NotNull(message = "Anesthesia Type field required")
    @Enumerated
    private AnesthesiaEnum anesthesiaType;

    @NotNull(message = "Date field required")
    @DatePattern
    private String date;


//    @JsonCreator
//    public SurgicalListCreateRequest(
//            @JsonProperty("patientId") Long patientId,
//            @JsonProperty("surgicalName") String surgicalName,
//            @JsonProperty("anesthesiaType") AnesthesiaEnum anesthesiaType
//    ) {
//        this.patientId = patientId;
//        this.surgicalName = surgicalName;
//        this.anesthesiaType = anesthesiaType;
//    }

    public SurgicalListCreateRequest() {
    }
}
