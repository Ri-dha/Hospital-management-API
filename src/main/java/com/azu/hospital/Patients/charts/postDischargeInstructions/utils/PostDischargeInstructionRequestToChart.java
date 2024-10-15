package com.azu.hospital.Patients.charts.postDischargeInstructions.utils;


import com.azu.hospital.Patients.charts.postDischargeInstructions.request.PostDischargeInstructionRequest;
import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;

public class PostDischargeInstructionRequestToChart {
  public static PostDischargeInstructionsChart requestToChart(PostDischargeInstructionRequest request){
    return new PostDischargeInstructionsChart.Builder()
            .withAnticoagulantAdvisement(request.anticoagulantAdvisement())
            .withDoctorName(request.doctorName())
            .withPhoneNumber(request.phoneNumber())
            .withHospitalName(request.hospitalName())
            .withPatientSignature(request.patientSignature())
            .withPatientSignatureDate(request.patientSignatureDate())
            .withPatientSignatureTime(request.patientSignatureTime())
            .withRegisteredNurseName(request.registeredNurseName())
            .withRegisteredDate(request.registeredDate())
            .withRegisteredTime(request.registeredTime())
            .build();
  }
}
