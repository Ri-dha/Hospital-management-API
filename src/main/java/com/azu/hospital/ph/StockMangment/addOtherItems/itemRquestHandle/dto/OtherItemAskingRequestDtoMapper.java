package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dto;

import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OtherItemAskingRequestDtoMapper implements Function<OtherItemAskingRequest, OtherItemAskingRequestDto> {
    @Override
    public OtherItemAskingRequestDto apply(OtherItemAskingRequest request) {
        return new OtherItemAskingRequestDto(
                request.getRequestId(),
                request.getItemId(),
                request.getExistsTableSpecialItemName(),
                request.getQuantity(),
                request.getRequestStatus(),
                request.getSignature().getId(),
                request.getRejectCause() == null ? null : request.getRejectCause(),
                request.getNote(),
                request.getCreateAt(),
                request.getUpdateAt() == null ? null : request.getUpdateAt()
        );
    }
}
