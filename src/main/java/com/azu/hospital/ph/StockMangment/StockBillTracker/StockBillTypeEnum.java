package com.azu.hospital.ph.StockMangment.StockBillTracker;

public enum StockBillTypeEnum {
    Consumables("Consumables"),
    Drugs("Drugs"),
    OtherItems("Other Items"),
    LabItems("Lab Items");

    private final String name;

    StockBillTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }




}
