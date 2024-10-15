package com.azu.hospital.bulding.floor.dto;

import com.azu.hospital.bulding.ward.dto.WardDto;
import com.azu.hospital.bulding.ward.entity.Ward;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
public class FloorWithWardsDto {

    private Long floorId;

    private String floorNumber;

    private String floorName;

    private Instant createdAt;
    private List<WardDto> wards;


    public FloorWithWardsDto() {
    }
    public FloorWithWardsDto(
            Long floorId,
            String floorNumber,
            String floorName,
            Instant createdAt,
            List<WardDto> wards
    ) {
        this.floorId = floorId;
        this.floorName = floorName;
        this.floorNumber = floorNumber;
        this.createdAt = createdAt;
        this.wards = wards;
    }




}
