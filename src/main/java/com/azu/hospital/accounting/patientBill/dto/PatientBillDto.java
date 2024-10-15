package com.azu.hospital.accounting.patientBill.dto;

import com.azu.hospital.utils.enums.accounting.PatientBillEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PatientBillDto {

    private Long id;

    private Long patientId;

    private String patientName;

    private String note;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal fullPrice;

    private BigDecimal discount;

    private PatientBillEnum type;

    private Instant createdAt;

    private Instant updatedAt;


    public PatientBillDto(
            Long id,
            Long patientId,
            String patientName,
            String note,
            Integer quantity,
            BigDecimal price,
            BigDecimal fullPrice,
            BigDecimal discount,
            PatientBillEnum type,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.note = note;
        this.quantity = quantity;
        this.price = price;
        this.fullPrice = fullPrice;
        this.discount = discount;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PatientBillDto() {
    }
}
