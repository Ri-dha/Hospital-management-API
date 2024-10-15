package com.azu.hospital.utils.humanResourceUtils;

public class MonthAmountDto {
    private String month;
    private Long amount;

    public MonthAmountDto(String month, Long amount) {
        this.month = month;
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }
}
