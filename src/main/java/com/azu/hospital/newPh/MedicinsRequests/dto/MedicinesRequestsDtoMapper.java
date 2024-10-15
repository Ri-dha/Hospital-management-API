package com.azu.hospital.newPh.MedicinsRequests.dto;

import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class MedicinesRequestsDtoMapper implements Function<MedicinesRequests, MedicinesRequestsDto>{
    @Override
    public MedicinesRequestsDto apply(MedicinesRequests medicinesRequests) {
        return new MedicinesRequestsDto(
                medicinesRequests.getId(),
                medicinesRequests.getType(),
                medicinesRequests.getRequestStatus(),
                medicinesRequests.getTimesDay(),
                medicinesRequests.getTimesServing(),
                medicinesRequests.getDoes(),
                medicinesRequests.getQuantity(),
                medicinesRequests.getRoa(),
                medicinesRequests.getNote(),
                medicinesRequests.getMedicines().getId(),
                medicinesRequests.getMedicines().getName()
        );
    }
}
