package com.azu.hospital.bulding.ward.medicalDevice.entity;


import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Entity
@Getter
@Setter
@Table(name = "ward_medical_devices")
public class WardMedicalDevice {

    @Id
    @SequenceGenerator(
            name = "ward_device_seq",
            sequenceName = "ward_device_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer count;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(columnDefinition = "TEXT")
    private String receivedNote;

    private WardInventoryRequestEnum status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private OtherItems device;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    @JsonIgnore
    private Ward ward;

    private Instant createdAt;

    private Instant updatedAt;

    public WardMedicalDevice() {
    }

    public WardMedicalDevice(Integer count , WardInventoryRequestEnum status , String note) {
        this.count = count;
        this.status =status;
        this.note = note;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


}
