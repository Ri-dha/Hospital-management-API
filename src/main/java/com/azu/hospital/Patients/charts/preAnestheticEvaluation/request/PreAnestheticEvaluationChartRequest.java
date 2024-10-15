package com.azu.hospital.Patients.charts.preAnestheticEvaluation.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.Plan;
import com.azu.hospital.Patients.charts.physical_assment.AsaStatus;
import com.azu.hospital.Patients.charts.physical_assment.LabsECG;
import com.azu.hospital.Patients.charts.physical_assment.PhysicalExam;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PreAnestheticEvaluationChartRequest {
    private String proposedProcedure;
    private PhysicalExam physicalExam;
    private LabsECG labsEcg;
    private AsaStatus asa;
    private Plan plan;
    private String anesthesiologist;
    private String date;
    private String time;


    public PreAnestheticEvaluationChartRequest(
            String proposedProcedure,
            PhysicalExam physicalExam,
            LabsECG labsEcg,
            AsaStatus asa,
            Plan plan,
            String anesthesiologist,
            String date,
            String time
    ) {
        this.proposedProcedure = proposedProcedure;
        this.physicalExam = physicalExam;
        this.labsEcg = labsEcg;
        this.asa = asa;
        this.plan = plan;
        this.anesthesiologist = anesthesiologist;
        this.date = date;
        this.time = time;
    }
}
