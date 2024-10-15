package com.azu.hospital.ph.Sales.Request;


import com.azu.hospital.ph.Sales.solid_item_list.request.SoldItemsUpdateRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SoldBillRegistrationRequest {


    private BigDecimal fullPrice;
    private Long patientId;
    private SoldItemsUpdateRequest itemsList;


    @JsonCreator
    public SoldBillRegistrationRequest(@NotNull

                                       @JsonProperty("drugTradeName") String itemName,
                                       @JsonProperty("patientId") Long patientId,
                                       @JsonProperty("fullPrice") BigDecimal fullPrice,
                                       @JsonProperty("quantity") Integer quantity
    ) {
        this.fullPrice = fullPrice;
        this.patientId = patientId;
        this.itemsList = new SoldItemsUpdateRequest(itemName, quantity);
    }




}
