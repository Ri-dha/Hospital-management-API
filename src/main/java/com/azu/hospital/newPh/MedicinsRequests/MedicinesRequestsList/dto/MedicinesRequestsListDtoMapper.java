package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dto;


import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import com.azu.hospital.newPh.MedicinsRequests.dto.MedicinesRequestsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MedicinesRequestsListDtoMapper implements Function<MedicinesRequestsList, MedicinesRequestsListDto>{

    private final MedicinesRequestsDtoMapper medicinesRequestsDtoMapper;

    @Autowired
    public MedicinesRequestsListDtoMapper(MedicinesRequestsDtoMapper medicinesRequestsDtoMapper) {
        this.medicinesRequestsDtoMapper = medicinesRequestsDtoMapper;
    }

    @Override
    public MedicinesRequestsListDto apply(MedicinesRequestsList medicinesRequestsList) {
        return new MedicinesRequestsListDto(
               medicinesRequestsList.getId(),
                medicinesRequestsList.getPatient().getId(),
                medicinesRequestsList.getPatient().getPatientData().getFullName(),
                medicinesRequestsList.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial(),
                medicinesRequestsList.getPatient().getDoctorSpecials().getDoctor().getId(),
                medicinesRequestsList.getPatient().getPatientData().getGender(),
                medicinesRequestsList.getPatient().getPatientData().getHeight(),
                medicinesRequestsList.getPatient().getPatientData().getWeight(),
                medicinesRequestsList.getPatient().getPatientDate().getAdmissionDate(),
                medicinesRequestsList.getPatient().getBed().getBedNumber(),
                medicinesRequestsList.getPatient().getWard().getName(),
                medicinesRequestsList.getPatient().getPatientDate().getAge(),
                medicinesRequestsList.getPatient().getAllergySpecial(),
                medicinesRequestsList.getPatient().getDxSpecial(),
                medicinesRequestsList.getCreateAt(),
                medicinesRequestsList.getUpdateAt(),
                medicinesRequestsList.getMedicinesRequests()
                        .stream()
                        .map(medicinesRequestsDtoMapper)
                        .toList()
        );
    }
}
