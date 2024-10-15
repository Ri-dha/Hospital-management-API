package com.azu.hospital.newPh.Medicins.requests;

public record MedicinesCreateRecord(
        String name,
        String barCode,
        Long price,
        Long quantity
) {
}
