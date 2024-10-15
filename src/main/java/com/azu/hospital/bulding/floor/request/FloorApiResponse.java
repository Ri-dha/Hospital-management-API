package com.azu.hospital.bulding.floor.request;

import com.azu.hospital.bulding.floor.dto.FloorDto;
import com.azu.hospital.ph.utils.data.AbstractApiResponse;

public class FloorApiResponse extends AbstractApiResponse<FloorDto> {



    public FloorApiResponse(String message, boolean status, int statusCode, FloorDto data) {
        super(message, status, statusCode, data);

    }

    public FloorApiResponse() {
        super();
    }



}
