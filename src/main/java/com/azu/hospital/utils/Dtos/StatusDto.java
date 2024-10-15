package com.azu.hospital.utils.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusDto<T> {

    private T status;

    public StatusDto(T status) {
        this.status = status;
    }
}
