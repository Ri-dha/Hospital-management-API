package com.azu.hospital.ph.mediciens.icd_code.entity;
public class IcdCodClass {

    private String title;
    private String cell2;
    private String cell3;
    private String cell4;
    private String cell5;
    private String cell6;
    private String cell7;



    public IcdCodClass() {

    }

    public IcdCodClass(String title, String cell2, String cell3, String cell4, String cell5, String cell6, String cell7) {
        this.title = title;
        this.cell2 = cell2;
        this.cell3 = cell3;
        this.cell4 = cell4;
        this.cell5 = cell5;
        this.cell6 = cell6;
        this.cell7 = cell7;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getCell3() {
        return cell3;
    }

    public void setCell3(String cell3) {
        this.cell3 = cell3;
    }

    public String getCell4() {
        return cell4;
    }

    public void setCell4(String cell4) {
        this.cell4 = cell4;
    }

    public String getCell5() {
        return cell5;
    }

    public void setCell5(String cell5) {
        this.cell5 = cell5;
    }

    public String getCell6() {
        return cell6;
    }

    public void setCell6(String cell6) {
        this.cell6 = cell6;
    }

    public String getCell7() {
        return cell7;
    }

    public void setCell7(String cell7) {
        this.cell7 = cell7;
    }

    @Override
    public String toString() {
        return "Substance " + title + " {\n" +
                "    Poisoning Accidental  " + cell2 + "\n" +
                "    Poisoning Intentional self-harm " + cell3 + "\n" +
                "    Poisoning Assault " + cell4 + "\n" +
                "    Poisoning Undetermined " + cell5 + "\n" +
                "    Adverse effect " + cell6 + "\n" +
                "    Underdosing " + cell7 + "\n" +
                "}";
    }
}
