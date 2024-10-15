package com.azu.hospital.lab_collection.test_list.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


public record CreateLabListRequest(
        @NotNull
        @Valid
        List<LabListDataRequest> labList

) {

}
