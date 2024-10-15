package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity;

import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devices_expanse_table")
@Getter
@Setter
public class DeviceExpanseTable {

    @Id
    @SequenceGenerator(
            name = "devices_expanse_table_deviceId_seq",
            sequenceName = "devices_expanse_table_deviceId_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "devices_expanse_table_deviceId_seq"
    )

    private Long deviceId;

    @Column(nullable = false)
    @NotNull(message = "Count Required")
    private Integer quantity = 0;

    @Column(nullable = false)
    @NotNull(message = "Place Required")
    @Enumerated(EnumType.STRING)
    private OtherItemRequestPlaces devicePlace;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId")
    private DeviceExistsTable deviceExistsTable;




    public DeviceExpanseTable() {
    }

    public DeviceExpanseTable(
            Long deviceId,
            Integer quantity,
            OtherItemRequestPlaces devicePlace
    ) {
        this.deviceId = deviceId;
        this.quantity = quantity;
        this.devicePlace = devicePlace;
    }

    public DeviceExpanseTable(

            Integer quantity,
            OtherItemRequestPlaces devicePlace

    ) {
        this.quantity = quantity;
        this.devicePlace = devicePlace;
    }



}
