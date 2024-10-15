package com.azu.hospital.books.copyTo.dto;

import java.time.Instant;

public record CopyToDto (
        Long id,
        String departmentName,
        Instant createdAt
){
}
