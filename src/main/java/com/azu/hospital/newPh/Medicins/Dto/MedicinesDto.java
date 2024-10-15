package com.azu.hospital.newPh.Medicins.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MedicinesDto {

    private Long id;

    private String name;
    private String barCode;
    private Long price;
    private Long quantity;
    private Instant createdAt;
    private Instant updateAt;

    public MedicinesDto() {
    }

    public MedicinesDto(Long id, String name, String barCode, Long price, Long quantity, Instant createdAt, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.barCode = barCode;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public MedicinesDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
