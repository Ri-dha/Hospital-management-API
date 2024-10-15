package com.azu.hospital.ecg.internal.ecgInternalResults.dto;

import com.azu.hospital.ecg.internal.ecgInternalResults.entity.InternalEcgResult;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class InternalEcgMapper implements Function<InternalEcgResult, InternalEcgDto> {

    @Override
    public InternalEcgDto apply(InternalEcgResult internalEcgResult) {
        return new InternalEcgDto(
                internalEcgResult.getId(),
                internalEcgResult.getNote(),
                internalEcgResult.getEcg().getId(),
                internalEcgResult.getFiles() == null ? null : internalEcgResult.getFiles()
        );
    }
}
