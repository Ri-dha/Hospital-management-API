package com.azu.hospital.Patients.charts.physical_assessment_enm;

public enum Fluid {
    NORMAL_SALINE("0.9% NaCl (Normal Saline Solution, NSS)"),
    D5W("Dextrose 5% in Water (D5W)"),
    D5LRS("Lactated Ringer’s 5% Dextrose in Water (D5LRS)"),
    RINGERS("Ringer’s Solution"),
    NA_CL_0_45("0.45% Sodium Chloride (0.45% NaCl)"),
    NA_CL_0_33("0.33% Sodium Chloride (0.33% NaCl)"),
    NA_CL_0_225("0.225% Sodium Chloride (0.225% NaCl)"),
    D2_5W("2.5% Dextrose in Water (D2.5W)"),
    D10W("Dextrose 10% in Water (D10W)"),
    D20W("Dextrose 20% in Water (D20W)"),
    D50W("Dextrose 50% in Water (D50W)"),
    HUMAN_ALBUMIN("Human Albumin"),
    LMWD("Low-molecular-weight Dextrans (LMWD)"),
    HMWD("High-molecular-weight Dextrans (HMWD)"),
    ETHERIFIED_STARCH("Etherified Starch"),
    GELATIN("Gelatin"),
    PPF("Plasma Protein Fraction (PPF)"),
    PLASMA_LYTE_148("Plasma-Lyte 148");

    private final String symbol;


    Fluid(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
