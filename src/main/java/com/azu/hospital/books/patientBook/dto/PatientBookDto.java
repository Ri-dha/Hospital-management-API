package com.azu.hospital.books.patientBook.dto;


import com.azu.hospital.books.patientBook.PatientBookType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientBookDto {

    private Long bookId;

    private PatientBookType patientBookType;

    private Long patientId;
    private String patientName;

    private Long nurseId;
    private String nurseName;

    private Long wardManagerId;
    private String wardManagerName;

    private Long hospitalManagerId;
    private String hospitalManagerName;

    private Long doctorId;
    private String doctorName;


    public PatientBookDto(Long bookId, PatientBookType patientBookType,
                          Long patientId, String patientName,
                          Long nurseId, String nurseName,
                          Long wardManagerId, String wardManagerName,
                          Long hospitalManagerId, String hospitalManagerName,
                          Long doctorId, String doctorName) {
        this.bookId = bookId;
        this.patientBookType = patientBookType;
        this.patientId = patientId;
        this.patientName = patientName;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.wardManagerId = wardManagerId;
        this.wardManagerName = wardManagerName;
        this.hospitalManagerId = hospitalManagerId;
        this.hospitalManagerName = hospitalManagerName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }
}
