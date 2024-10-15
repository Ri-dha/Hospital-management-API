package com.azu.hospital.bulding.ward.wardBed.request;


import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BabyInfo {

    private Long id;

    private String name;

    private Long motherId;

    private String motherName;

    private Long wardId;
    private String wardName;
    private Long bedId;
    private String bedName;

    public BabyInfo(PrematureBaby prematureBaby) {
        this.id = prematureBaby.getId();
        this.name = prematureBaby.getName();
        this.motherId = prematureBaby.getPatient().getId();
        this.motherName = prematureBaby.getPatient().getPatientData().getFullName();
        this.wardId = prematureBaby.getWard().getWardId();
        this.wardName = prematureBaby.getWard().getName();
        this.bedId = prematureBaby.getBed().getId();
        this.bedName = prematureBaby.getBed().getBedNumber();
    }
}
