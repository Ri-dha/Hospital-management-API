package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dto.DrugRequestHandlerListDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dto.DrugRequestHandlerListDtoMapper;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DrugRequestHandlerListGetService {
    private final DrugRequestHandlerListDao dao;
    private final DrugRequestHandlerListDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public DrugRequestHandlerListGetService(DrugRequestHandlerListDao dao, DrugRequestHandlerListDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public DrugRequestHandlerListDto getDrugListById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getDrugRequestHandlerListById(id)
                .map(mapper).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

    }

    public Page<DrugRequestHandlerListDto> getDrugRequestHandlerListAllWithFilter(String patientName, Pageable pageable) {
        Page<DrugRequestHandlerList> drugRequestHandlerListPage = dao.getDrugRequestHandlerListAllWithFilter(patientName, pageable);

        List<DrugRequestHandlerListDto> drugRequestHandlerListDtoList = drugRequestHandlerListPage.getContent()
                .stream()
                .map(mapper)
                .toList();

        return new PageImpl<>(drugRequestHandlerListDtoList, pageable, drugRequestHandlerListPage.getTotalElements());
    }

    public List<DrugRequestHandlerListDto> getDrugRequestHandlerListByPatientId(Long patientId) {
        return dao.getDrugRequestHandlerListByPatientId(patientId)
                .stream()
                .map(mapper)
                .toList();
    }


    public Page<DrugRequestHandlerListDto> getDrugRequestHandlerListAccordingToUpdatedAt(Pageable pageable) {
        Page<DrugRequestHandlerList> drugRequestHandlerListPage = dao.getDrugRequestHandlerListAccordingToUpdatedAt(pageable);

        List<DrugRequestHandlerListDto> drugRequestHandlerListDtoList = drugRequestHandlerListPage.getContent().stream()
                .map(mapper)
                .toList();

        return new PageImpl<>(drugRequestHandlerListDtoList, pageable, drugRequestHandlerListPage.getTotalElements());
    }
}
