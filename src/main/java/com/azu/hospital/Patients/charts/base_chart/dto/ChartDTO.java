package com.azu.hospital.Patients.charts.base_chart.dto;

import java.util.Map;

public class ChartDTO {
    private Long id;
    private Long patientId;
    private String chartType; 
    private Map<String, Object> chartData; 
  
}