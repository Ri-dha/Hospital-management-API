package com.azu.hospital.bulding.ward.wardDevice.entity;


import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Entity
@Data
@Table(name = "ward_device")
public class WardDevice {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private OtherItems device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    private Instant createdAt;

    private Instant updatedAt;

    public WardDevice() {
    }

    public WardDevice(Integer count , WardInventoryRequestEnum status , String note) {
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
