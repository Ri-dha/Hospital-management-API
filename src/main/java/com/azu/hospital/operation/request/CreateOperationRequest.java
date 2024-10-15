package com.azu.hospital.operation.request;


import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.utils.enums.AnesthesiaType;
import com.azu.hospital.utils.enums.operation.OperationAnesthetization;
import com.azu.hospital.utils.enums.operation.OperationTriage;
import com.azu.hospital.utils.enums.operation.OperationTypes;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record CreateOperationRequest(

        @NotNull(message = "Operation Type is Required")
        @DatePattern
        String operationDate,

        String note,

        @NotNull(message = "Operation Type is Required")
        @Enumerated
        AnesthesiaType anesthetization,

        @NotNull(message = "Operation Type is Required")
        Long surgicalId,

        @NotNull(message = "Operation Type is Required")
        @Enumerated
        OperationTriage triage
) {
}
