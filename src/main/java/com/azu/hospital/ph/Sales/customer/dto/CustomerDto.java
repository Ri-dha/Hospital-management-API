package com.azu.hospital.ph.Sales.customer.dto;

import com.azu.hospital.utils.enums.Gender;


public record CustomerDto(
         Long customerId,
         String name,
         String age,
         Gender gender,
         String contact,
         String doctorName
) {
}
