package com.azu.hospital.lab_collection.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateTimeTest {

    private Long count;
    private Integer hour;

    public DateTimeTest(Long count, Integer hour) {
        this.count = count;
        this.hour = hour;
    }

    public DateTimeTest() {
    }
}
