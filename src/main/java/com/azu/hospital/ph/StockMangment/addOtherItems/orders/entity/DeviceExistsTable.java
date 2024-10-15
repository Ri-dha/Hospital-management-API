package com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity;

import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.utils.enums.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devices_exists_table")
@Getter
@Setter
public class DeviceExistsTable {

    @Id
    @SequenceGenerator(
            name = "devices_exists_table_deviceId_seq",
            sequenceName = "devices_exists_table_deviceId_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "devices_exists_table_deviceId_seq"
    )

    private Long deviceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private OtherItems deviceDetails;

    @Column(nullable = false)
    @NotNull(message = "Count Required")
    private Integer quantity;

    private Boolean isAvailable = true;

    @Column(nullable = false)
    private Boolean isDeviceWorking = true;

    private DeviceType type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId")
    private DeviceExpanseTable expanseTable;


    public DeviceExistsTable() {
    }

    public DeviceExistsTable(
            Long deviceId,
            OtherItems deviceDetails,
            Integer quantity,
            Boolean isDeviceWorking,
            Boolean isAvailable,
            DeviceType type
    ) {
        this.deviceId = deviceId;
        this.deviceDetails = deviceDetails;
        this.quantity = quantity;
        this.isDeviceWorking = isDeviceWorking;
        this.isAvailable = isAvailable;
        this.type = type;

    }

    public DeviceExistsTable(

            Integer quantity,
            Boolean isDeviceWorking
            , Boolean isAvailable
    ) {
        this.quantity = quantity;
        this.isDeviceWorking = isDeviceWorking;
        this.isAvailable = isAvailable;
    }


    public DeviceExistsTable(String itemName, DeviceType deviceType) {
        this.deviceDetails = new OtherItems(itemName, deviceType);
    }
}
