package com.azu.hospital.bulding.ward.wardBed.dto;

import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDto;
import com.azu.hospital.bulding.ward.wardBed.request.BabyInfo;
import com.azu.hospital.bulding.ward.wardBed.request.BedPatientInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BedDto {

    private Long id;

    private String bedNumber;

    private BedPatientInfo patientInfo;

    private PrematureBabyDto prematureBaby;

    private Boolean IsBedReserved;

    private Instant createdAt;

    private Instant updatedAt;
    public BedDto() {
    }

    public BedDto(
            Long id,
            String bedNumber  ,
            BedPatientInfo patientInfo,
            PrematureBabyDto prematureBaby,
            Boolean IsBedReserved,
            Instant createdAt ,
            Instant updatedAt
    ) {
        this.id = id;
        this.bedNumber = bedNumber;
        this.patientInfo = patientInfo;
        this.prematureBaby = prematureBaby;
        this.IsBedReserved = IsBedReserved;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
