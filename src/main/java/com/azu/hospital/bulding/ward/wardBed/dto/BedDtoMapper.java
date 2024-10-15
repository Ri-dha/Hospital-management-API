package com.azu.hospital.bulding.ward.wardBed.dto;

import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDto;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.bulding.ward.wardBed.request.BabyInfo;
import com.azu.hospital.bulding.ward.wardBed.request.BedPatientInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BedDtoMapper {
    public BedDto toDto(Bed bed){
        return Optional.ofNullable(bed).map(b->
                new BedDto(
                bed.getId(),
                bed.getBedNumber(),
                bed.getPatient() == null ? null : new BedPatientInfo(bed.getPatient()),
                bed.getPrematureBaby() == null ? null : new PrematureBabyDto(bed.getPrematureBaby()),
                bed.getIsBedReserved(),
                bed.getCreatedAt() ,
                bed.getUpdatedAt()
                )
        ).orElseThrow();

    }
}
