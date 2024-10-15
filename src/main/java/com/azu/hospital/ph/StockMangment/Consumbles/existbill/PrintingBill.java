package com.azu.hospital.ph.StockMangment.Consumbles.existbill;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class PrintingBill {

    private String billNo;

    private String hospitalName;

    private String fromStore;

    private String toStore;

    private Instant CreatedDate;

    private String createdBy;

    private String requestAskBy;

    private List<PrintingBillItemList> itemLists;


    String billStatus;

    public PrintingBill() {
        this.itemLists = new ArrayList<>();
    }

    public PrintingBill(String billNo, String hospitalName, String fromStore,
                        String toStore, Instant CreatedDate, String createdBy, String requestAskBy,
                        List<PrintingBillItemList> itemLists) {
        this.billNo = billNo;
        this.hospitalName = hospitalName;
        this.fromStore = fromStore;
        this.toStore = toStore;
        this.CreatedDate = CreatedDate;
        this.createdBy = createdBy;
        this.requestAskBy = requestAskBy;
        this.itemLists = itemLists;
    }
}
