package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "asking_requests")
@Getter
@Setter
public class OtherItemAskingRequest {

    @Id
    @SequenceGenerator(
            name = "asking_requests_id_seq",
            sequenceName = "asking_requests_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "asking_requests_id_seq"
    )
    private Long requestId;
    @NotNull(message = "item required")
    private Long itemId;
    @NotNull(message = "quantity required")
    private Integer quantity;
    @NotNull(message = "requestStatus required")
    private UnitInventoryRequestEnum requestStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceExpanseTable> expanseTable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceExistsTable> existsTable;

    @Column(nullable = false)
    @NotNull(message = "Place Required")
    @Enumerated(EnumType.STRING)
    private OtherItemRequestPlaces devicePlace;
    // TODO: 11/6/2023 Places Type

    private Long placeId;
    // TODO: 11/6/2023 place id

    @Column(columnDefinition = "TEXT")
    private String rejectCause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BaseUser signature;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(updatable = false)
    private Instant createAt;

    private Instant updateAt;


    public OtherItemAskingRequest() {
    }

    public OtherItemAskingRequest(
            Integer quantity,
            OtherItemRequestPlaces devicePlace
    ) {
        this.quantity = quantity;
        this.devicePlace = devicePlace;
    }


    @PrePersist
    public void getCrateAt(){
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void getUpdate(){
        this.updateAt = Instant.now();
    }


    public List<DeviceExpanseTable> getExpanseTable() {
        return expanseTable;
    }

    public void setExpanseTable(List<DeviceExpanseTable> expanseTable) {
        this.expanseTable = expanseTable;
    }

    public List<DeviceExistsTable> getExistsTable() {
        return existsTable;
    }

    public List<DeviceExistsTable> getExistsTableSpecialDtoMapper() {
        return existsTable
                .stream()
                .map(d ->{
                    return new DeviceExistsTable(
                            d.getDeviceDetails().getItemName(),
                            d.getDeviceDetails().getDeviceType()
                    );
                }).collect(Collectors.toList());
    }

    public String getExistsTableSpecialItemName() {
        return existsTable
                .stream()
                .map(DeviceExistsTable::getDeviceDetails)
                .map(OtherItems::getItemName)
                .collect(Collectors.joining(", "));
    }

    public void setExistsTable(List<DeviceExistsTable> existsTable) {
        this.existsTable = existsTable;
    }
}
