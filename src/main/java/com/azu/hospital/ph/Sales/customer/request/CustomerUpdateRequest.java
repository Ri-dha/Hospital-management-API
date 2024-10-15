package com.azu.hospital.ph.Sales.customer.request;


import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CustomerUpdateRequest(
        String name,
        String age,
        @Enumerated(EnumType.STRING)
        Gender gender,
        String contact,
        String doctorName
) {
}
