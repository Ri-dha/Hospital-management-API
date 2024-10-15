package com.azu.hospital.HomeCard.dto;

import java.time.LocalDate;
import java.util.Map;

public record DashBoardOperationAnalysisDto (
        Map<LocalDate, DayData> dailyCounts
) {
    public static record DayData(Long totalAccepted, Long totalCanceled) {}
}
