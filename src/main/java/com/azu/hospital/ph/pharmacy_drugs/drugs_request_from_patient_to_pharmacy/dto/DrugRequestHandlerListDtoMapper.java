package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dto;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDtoMapper;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DrugRequestHandlerListDtoMapper implements Function<DrugRequestHandlerList, DrugRequestHandlerListDto> {

    private final DrugRequestHandlerDtoMapper drugRequestHandlerDtoMapper;

    @Autowired
    public DrugRequestHandlerListDtoMapper(DrugRequestHandlerDtoMapper drugRequestHandlerDtoMapper) {
        this.drugRequestHandlerDtoMapper = drugRequestHandlerDtoMapper;
    }

    @Override
    public DrugRequestHandlerListDto apply(DrugRequestHandlerList drugRequestHandlerList) {

        return new DrugRequestHandlerListDto(
                drugRequestHandlerList.getRequestListId(),
                drugRequestHandlerList.getPatient().getId(),
                drugRequestHandlerList.getPatient().getPatientData().getFullName(),
                drugRequestHandlerList.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial(),
                drugRequestHandlerList.getPatient().getDoctorSpecials().getDoctor().getId(),
                drugRequestHandlerList.getPatient().getPatientData().getGender(),
                drugRequestHandlerList.getPatient().getPatientData().getHeight(),
                drugRequestHandlerList.getPatient().getPatientData().getWeight(),
                drugRequestHandlerList.getPatient().getPatientDate().getAdmissionDate(),
                drugRequestHandlerList.getPatient().getBed().getBedNumber(),
                drugRequestHandlerList.getPatient().getWard().getName(),
                drugRequestHandlerList.getPatient().getPatientDate().getAge(),
                drugRequestHandlerList.getPatient().getAllergySpecial(),
                drugRequestHandlerList.getPatient().getDxSpecial(),
                drugRequestHandlerList.getPatient().getDoctorSpecials().getDoctor().getId(),
                drugRequestHandlerList.getDrugRequestHandlers()
                        .stream()
                        .map(drugRequestHandlerDtoMapper)
                        .toList(),
                drugRequestHandlerList.getCreateAt(),
                drugRequestHandlerList.getUpdateAt(),
                drugRequestHandlerList.getAcceptedCount() != null ? drugRequestHandlerList.getAcceptedCount() : 0,
                drugRequestHandlerList.getRejectedCount() != null ? drugRequestHandlerList.getRejectedCount() : 0,
                drugRequestHandlerList.isCompleted()

        );


    }
}
