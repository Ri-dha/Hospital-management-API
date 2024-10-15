package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.request;

import com.azu.hospital.utils.enums.storeHose.StoreHoseItemEnum;
import com.azu.hospital.utils.enums.storeHose.StoreHoseStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStoreHoseRequest {

    @NotNull
    private Long itemId;

    @NotNull
    private Integer count;

    private String note;


    public CreateStoreHoseRequest() {
    }

}
