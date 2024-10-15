package com.azu.hospital.hospital_info.dto;

import com.azu.hospital.hospital_info.entity.HospitalInfo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class HospitalInfoDtoMapper implements Function<HospitalInfo, HospitalInfoDto> {
    @Override
    public HospitalInfoDto apply(HospitalInfo hospitalInfo) {
        return new HospitalInfoDto(
                hospitalInfo.getId(),
                hospitalInfo.getName(),
                hospitalInfo.getImage()
        );
    }
}
