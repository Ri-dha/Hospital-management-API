package com.azu.hospital.utils.enums.ultrasound;


public enum UltrasoundTypeEnum {

    Transvaginal("Transvaginal"),
    ColorDoppler("Color Doppler"),
    Breast("Breast"),
    ThreeDimensional("3D"),
    Thyroid ("Thyroid"),
    Abdominal ("Abdominal"),
    Vascular ("Vascular"),
    Renal ("Renal"),
    Pelvic ("Pelvic"),
    Liver ("Liver"),
    Transabdominal("Transabdominal"),
    Obstetric("Obstetric"),
    Transrectal("Transrectal"),
    CarotidAndAbdominalAorta("Carotid and Abdominal Aorta"),
    Carotid("Carotid"),
    FourDimensional("4D"),
    DuplexDoppler("Duplex Doppler"),
    Scrotal("Scrotal"),
    Musculoskeletal("Musculoskeletal"),
    Echocardiogram("Echocardiogram"),
    Sonogram("Sonogram");

    private String name;

    UltrasoundTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
