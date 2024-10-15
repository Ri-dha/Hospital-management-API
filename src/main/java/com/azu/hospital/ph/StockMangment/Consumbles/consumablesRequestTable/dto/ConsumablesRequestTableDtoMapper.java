package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ConsumablesRequestTableDtoMapper implements Function<ConsumablesRequestTable, ConsumablesRequestTableDto> {
    @Override
    public ConsumablesRequestTableDto apply(ConsumablesRequestTable consumablesRequestTable) {
        return  new ConsumablesRequestTableDto(
                consumablesRequestTable.getRequestId(),
                consumablesRequestTable.getConsumableName(),
                consumablesRequestTable.getQuantity(),
                consumablesRequestTable.getRequestStatus(),
                consumablesRequestTable.getRejectCause() == null ? null : consumablesRequestTable.getRejectCause(),
                consumablesRequestTable.getCreatedBy(),
                consumablesRequestTable.getLastModifiedBy() == null ? null :
                        consumablesRequestTable.getLastModifiedBy(),
                consumablesRequestTable.getCreateAt(),
                consumablesRequestTable.getUpdateAt() == null ? null : consumablesRequestTable.getUpdateAt()
        );
    }
}
