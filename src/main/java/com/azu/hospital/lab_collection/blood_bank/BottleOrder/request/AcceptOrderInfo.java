package com.azu.hospital.lab_collection.blood_bank.BottleOrder.request;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.AcceptOrderInfoData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AcceptOrderInfo {

    @NotNull
    @NotEmpty
    @Valid
    private List<AcceptOrderInfoData> data;

    public AcceptOrderInfo() {
    }
}
