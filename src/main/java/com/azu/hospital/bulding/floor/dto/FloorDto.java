package com.azu.hospital.bulding.floor.dto;
import lombok.Data;

import java.time.Instant;

@Data
public class FloorDto  {

   private Long floorId;

    private String floorNumber;

    private String floorName;

    private Instant createdAt;



    public FloorDto(
            Long floorId,
            String floorNumber,
            String floorName,
            Instant createdAt
    ) {
        this.floorId = floorId;
        this.floorName = floorName;
        this.floorNumber = floorNumber;
        this.createdAt = createdAt;
    }

}
