package com.azu.hospital.newPh.MedicinsRequests.requests;

import com.azu.hospital.ph.mediciens.type.MedicineType;

public record MedicinesRequestsCreate(
        Long medicinesId,
        String does,
        String note,
        Long quantity,
        String roa,
        String timesDay,
        String timesServing,
        MedicineType type,
        String requestStatus
) {
}
