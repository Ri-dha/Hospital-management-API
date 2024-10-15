package com.azu.hospital.lab_collection.external.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDtoMapper;
import com.azu.hospital.lab_collection.external.dto.ExternalLabWithResultDto;
import com.azu.hospital.lab_collection.external.dto.ExternalLabWithResultDtoMapper;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class ExternalLabGetService {


    private final ExternalLabDao externalLabDao;

    private final ExtTestRequestDao extTestRequestDao;

    private final ExternalLabDtoMapper mapper;
    private final ExternalLabWithResultDtoMapper externalLabWithResultDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ExternalLabGetService(
            @Qualifier("externalLabRepo") ExternalLabDao externalLabDao,
            @Qualifier("testRequestRepo") ExtTestRequestDao extTestRequestDao,
            @Qualifier("externalLabDtoMapper") ExternalLabDtoMapper mapper,
            ExternalLabWithResultDtoMapper externalLabWithResultDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.externalLabDao = externalLabDao;
        this.extTestRequestDao = extTestRequestDao;
        this.mapper = mapper;
        this.externalLabWithResultDtoMapper = externalLabWithResultDtoMapper;
        this.messageReturn = messageReturn;
    }


    public Page<ExternalLabDto> getAllExternalLab(List<LabRequestStatusEnum> states , Pageable pageable){


        return externalLabDao.getAllExternalTest(states , pageable).map(mapper);
    }
    public Page<ExternalLabDto> getExternalLabNotArchived( Pageable pageable) {

        return externalLabDao.getAllExternalTestNotArchived(pageable).map(mapper);
    }

    public Page<ExternalLabDto> getExternalLabArchived(Pageable pageable) {

        return externalLabDao.getAllExternalTestArchived(pageable).map(mapper);
    }

    public ExternalLabDto getPatientInfo(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ExternalLabTest externalLabTest = externalLabDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("labTestNotFound")+" "+id
                ));

        return mapper.apply(externalLabTest);
    }

    public Optional<ExternalLabWithResultDto> getExternalLabByRequestId(Long labId) {
        return externalLabDao.getExternalLabByRequestId(labId)
                .map(externalLabWithResultDtoMapper);
    }
}
