package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto.ConsumablesRequestTableDto;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ConsumablesRequestTableListDto {

    private Long requestListId;
    private String Note;
    private Integer Quantity;
    private List<ConsumablesRequestTableDto> consumablesRequestTableList;
    private Instant createdAt;
    private Instant updatedAt;
    private Long createdBy;
    private String createdByName;
    private Long lastModifiedBy;
    private String lastModifiedByName;
    private UnitInventoryRequestEnum requestListState;

    public ConsumablesRequestTableListDto() {
    }

    public ConsumablesRequestTableListDto(Long requestListId, String note, Integer quantity, List<ConsumablesRequestTableDto> consumablesRequestTableList, Instant createdAt, Instant updatedAt, Long createdBy, String createdByName, Long lastModifiedBy, String lastModifiedByName, UnitInventoryRequestEnum requestListState) {
        this.requestListId = requestListId;
        Note = note;
        Quantity = quantity;
        this.consumablesRequestTableList = consumablesRequestTableList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedByName = lastModifiedByName;
        this.requestListState = requestListState;
    }
}
