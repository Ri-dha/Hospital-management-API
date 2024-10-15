package com.azu.hospital.ph.mediciens.type;

public enum MedicineType {

    TABLET("Tablet"),
    CAP("Capsule"),
    SYRUP("Syrup"),
    INJECTION("Injection"),
    CREAM("Cream"),
    DROPS("Drops"),
    POWDER("Powder"),
    INHALER("Inhaler"),
    SUPPOSITORY("Supplier"),
    PILL("Pill"),
    LOZENGE("Lozenge"),
    PASTE("Paste"),
    GEL("Gel"),
    LOTION("Lotion"),
    OINTMENT("Ointment"),
    OIL("Oil"),
    SOLUTION("Solution"),
    SUSPENSION("Suspension"),
    EMULSION("Emulsion"),
    GRANULES("Granules"),
    PATCH("Patch"),
    SPRAY("Spray"),
    VIAL("Vial"),
    INTRAVENOUS("Intravenous"),
    LIQUID("Liquid"),
    ELIXIR("Elixir"),
    AEROSOL("Aerosol"),
    LINIMENT("Liniment"),
    TINCTURE("Tincture"),
    PESSARY("Pessary"),
    ENEMA("Enema"),
    IMPLANT("Implant"),
    INTRAMUSCULAR("IntraMuscular"),
    INTRADERMAL("IntraDermal"),
    INTRATHECAL("IntraThecal"),
    RECTAL("Rectal"),
    SUBLINGUAL("Sublingual"),
    TRANSDERMAL("TransDermal"),
    TOPICAL("Topical"),
    VAGINAL("Vaginal"),
    OCULAR("Ocular"),
    INTRANASAL("IntraNasal"),
    NEBULIZER("Nebulizer"),
    INTRAUTERINE_DEVICE("IUD"),
    NASAL_SPRAY("Nasal Spray"),
    TRANSDERMAL_PATCH("TransDermal Patch"),
    OPHTHALMIC_SOLUTION("Ophthalmic Solution"),
    OPHTHALMIC_SUSPENSION("Ophthalmic Suspension"),
    OPHTHALMIC_OINTMENT("Ophthalmic Ointment"),
    EAR_DROPS("Ear Drops"),
    MOUTHWASH("Mouth Wash"),
    TRANSDERMAL_GEL("TransDermal Gel"),
    LIPOSPHERES("Lipospheres"),
    NANOPARTICLES("Nanoparticles"),
    MICROSPHERES("MicroSpheres"),
    LIPID_COMPLEX("Lipid complex"),
    HYDROGEL("Hydrogel"),
    BOLUS("Bolus"),
    PELLET("Pellet"),
    RESIN("Resin"),
    DRAGEE("Dragee"),
    TROCHE("Troche"),
    PASTILLE("Pastille"),
    DISC("Disc"),
    TEABAGS("Tea Bags"),
    DIAPHRAGM("Diaphragm"),
    CONDOM("Condom"),
    SPONGE("Sponge"),
    BREATH_STRIPS("Breath Strips"),
    INHALER_DRY_POWDER("Inhaler Dry Powder"),
    INHALER_METERED_DOSE("Inhaler Metered Dose"),
    INHALER_SOFT_MIST("Inhaler Soft Mist"),
    NEBULIZER_SOLUTION("Nebulizer Solution"),
    TRANSFEROSOME("Transferosome"),
    ETHOSOME("Ethosome"),
    NIOSOME("Niosome"),
    LIPOSOME("Liposome"),
    PREFILLED_SYRINGE("Prefilled Syringe"),
    PREFILLED_PEN("Prefilled Pen"),
    AMPULE("Ampule"),
    BLISTER_PACK("Blister Pack"),
    SACHET("Sachet"),
    METERED_DOSE_INHALER("Metered Dose Inhaler"),
    DRY_POWDER_INHALER("Dry Powder Inhaler"),
    SPIRIT("Spirit"),
    TOPICAL_GEL("Topical Gel"),
    PLASTER("Plaster"),
    SHAMPOO("Shampoo"),
    PILLS("Pills"),
    WAFER("Wafer"),
    INTRAVENOUS_FLUIDS("Intravenous Fluids");

    private  String medicineType;

    private final String value;

    MedicineType(String medicineType, String value) {
        this.medicineType = medicineType;
        this.value = value;
    }

    MedicineType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
