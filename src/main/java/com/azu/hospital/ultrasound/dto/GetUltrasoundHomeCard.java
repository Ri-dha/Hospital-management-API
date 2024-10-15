package com.azu.hospital.ultrasound.dto;

import lombok.Data;

@Data
public class GetUltrasoundHomeCard {

    private Long todayTests;

    private Long allTests;

    public GetUltrasoundHomeCard(Long todayTests, Long allTests) {
        this.todayTests = todayTests;
        this.allTests = allTests;
    }

    public GetUltrasoundHomeCard() {
    }
}
