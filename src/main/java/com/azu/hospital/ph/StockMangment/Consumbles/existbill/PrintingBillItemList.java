package com.azu.hospital.ph.StockMangment.Consumbles.existbill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintingBillItemList {
    private String itemName;
    private Integer count;

    public PrintingBillItemList() {
    }

    public PrintingBillItemList(String itemName, Integer count) {
        this.itemName = itemName;
        this.count = count;
    }

}
