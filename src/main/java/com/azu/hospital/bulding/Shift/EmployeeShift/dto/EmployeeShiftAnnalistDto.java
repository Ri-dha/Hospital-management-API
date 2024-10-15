package com.azu.hospital.bulding.Shift.EmployeeShift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeShiftAnnalistDto {


    private String wardName;

    private String fromTime;

    private String toTime;

    private String Date;

    private String day;

    private String nurseName;

    private String shiftName;

    private Integer sorted;



}
