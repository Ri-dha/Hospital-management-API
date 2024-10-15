package com.azu.hospital.ph.utils.favor;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseSearchEntity {

    private int searchCount;
    private int favorCount;


}
