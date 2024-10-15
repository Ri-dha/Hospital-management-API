package com.azu.hospital.ph.StockMangment.Consumbles.store_hose.dto;

import com.azu.hospital.utils.enums.storeHose.StoreHoseStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreHoseDto {
    private Long id;

    private String item;

    private Integer count;


    private String note;

    private String receivedNote;

    private StoreHoseStatusEnum state;


    public StoreHoseDto(Long id, String item, Integer count, String note, String receivedNote, StoreHoseStatusEnum state) {
        this.id = id;
        this.item = item;
        this.count = count;
        this.note = note;
        this.receivedNote = receivedNote;
        this.state = state;
    }

    public StoreHoseDto() {
    }

}
