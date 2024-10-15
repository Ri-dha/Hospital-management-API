package com.azu.hospital.statical_tables.nations.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationsDto {
    private Long id;
    private String nationsTitle;
    private Long nationsCode;


    public NationsDto(Long id, String nationsTitle, Long nationsCode) {
        this.id = id;
        this.nationsTitle = nationsTitle;
        this.nationsCode = nationsCode;
    }
}
