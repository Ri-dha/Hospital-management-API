package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDtoMapper;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugRequestHandlerServices {
    private final DrugRequestHandlerDao handlerDao;
    private final DrugRequestHandlerDtoMapper mapper;

    @Autowired
    public DrugRequestHandlerServices(
            @Qualifier("DrugRequestHandlerJpa") DrugRequestHandlerDao handlerDao,
            DrugRequestHandlerDtoMapper mapper) {
        this.handlerDao = handlerDao;
        this.mapper = mapper;
    }

    public DrugRequestHandlerDto getDrugRequestById(Long id) {
        return handlerDao.getRequestById(id)
                .stream()
                .map(mapper)
                .findFirst().orElse(null);
    }


    public Page<DrugRequestHandlerDto> getRequestByStatus(UnitInventoryRequestEnum status,
                                                          int page, int size, String sortField,
                                                          String sortDir) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        Page<DrugRequestHandler> drugRequestPage = handlerDao.getAllRequestsByStatusAndLastUpdateAsd(status, pageable);

        List<DrugRequestHandlerDto> dtoList = drugRequestPage.getContent()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, drugRequestPage.getTotalElements());
    }


    public Page<DrugRequestHandlerDto> getAllRequestBy(
            Long userId, Long drugId,
            UnitInventoryRequestEnum requestStatus,
            Pageable pageable

    ) {


        Page<DrugRequestHandler> drugRequestPage = handlerDao.getAllRequestsAccordingTo( userId,  drugId,  requestStatus,  pageable);

        List<DrugRequestHandlerDto> dtoList = drugRequestPage.getContent()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, drugRequestPage.getTotalElements());
    }


}
