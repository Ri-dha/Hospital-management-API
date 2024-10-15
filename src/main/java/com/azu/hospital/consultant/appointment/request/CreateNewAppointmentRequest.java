package com.azu.hospital.consultant.appointment.request;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class CreateNewAppointmentRequest {

        @NotNull
        private Long patientId;

        @NotNull
        @NumberFormat
        private Integer number;

        @NotNull
        @DatePattern
        private String date;

        @NotNull
        @BooleanFlag
        private Boolean isNew;

        public CreateNewAppointmentRequest() {
        }
}
