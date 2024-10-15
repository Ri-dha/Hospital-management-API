package com.azu.hospital.radiology.dto;

import lombok.Data;

@Data
public class GetCardNumberDto {

    private Long todayTests;
    private Long ctTest;
    private Long mriTest;
    private Long xRayTest;

    public GetCardNumberDto(Long todayTests, Long ctTest, Long mriTest, Long xRayTest) {
        this.todayTests = todayTests;
        this.ctTest = ctTest;
        this.mriTest = mriTest;
        this.xRayTest = xRayTest;
    }

    public GetCardNumberDto() {
    }
}
