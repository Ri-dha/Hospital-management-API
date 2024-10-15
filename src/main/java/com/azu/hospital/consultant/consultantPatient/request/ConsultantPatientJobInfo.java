package com.azu.hospital.consultant.consultantPatient.request;

import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
import com.azu.hospital.Patients.PatentEnum.JobTypeEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class ConsultantPatientJobInfo {



    @Enumerated
    private CertificationEnum certification;

    @NotBlank
    private String job;


    @Enumerated
    private JobTypeEnum jobType;





    public ConsultantPatientJobInfo() {
    }
}
