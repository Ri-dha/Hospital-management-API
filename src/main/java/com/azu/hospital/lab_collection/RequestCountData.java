package com.azu.hospital.lab_collection;

import lombok.Data;
import org.springframework.format.Printer;

import java.awt.print.PrinterJob;
import java.util.Scanner;

@Data
public class RequestCountData {
    private String labName;
    private Long count;

    public RequestCountData(String labName, Long count) {
        this.labName = labName;
        this.count = count;
    }

    public RequestCountData() {
    }


}
