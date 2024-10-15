package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.utils;


import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PostOperativeFollowUpCallChartRequest;

public class PostOperativeFollowUpCallRequestToChart {
  public static PostOperativeFollowUpCall requestToChart(PostOperativeFollowUpCallChartRequest request){
    return new PostOperativeFollowUpCall.Builder()
            .withDateOfCall(request.dateOfCall())
            .withLsmCaller(request.lsmCaller())
            .withNumberOfAttempts(request.numberOfAttempts())
            .withTime(request.time())
            .withProcedure(request.procedure())
            .withPainLevel(request.painLevel())
            .withLsmPhysicianSignature(request.lsmPhysicianSignature())
            .withAnesthesiologistSignature(request.anesthesiologistSignature())
            .withDateOfService(request.dateOfService())
            .withPatientExperiencing(request.patientExperiencing())
            .build();
  }
}
