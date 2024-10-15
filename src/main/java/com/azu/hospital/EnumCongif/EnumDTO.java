package com.azu.hospital.EnumCongif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumDTO {
    private int code;
    private String message;
    private LinkedMap<String, LinkedMap<String, Integer>> data;

}