package com.azu.hospital.radiology.external.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.external.dao.ExternalRadiologyTestDao;
import com.azu.hospital.radiology.external.dto.ExternalRadiologyTestDtoMapper;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalRadiologyTestStateService extends GenericBaseService {

    private final ExceptionsMessageReturn messageReturn;

    private final ExternalRadiologyTestDao externalRadiologyTestDao;

    private final ExternalRadiologyTestDtoMapper mapper;


    @Autowired
    public ExternalRadiologyTestStateService(
            @Qualifier("externalRadiologyTestRepo") ExternalRadiologyTestDao externalRadiologyTestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest, ExceptionsMessageReturn messageReturn,
            ExternalRadiologyTestDtoMapper mapper
    ) {
        super(jwtService, httpServletRequest);
        this.externalRadiologyTestDao = externalRadiologyTestDao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
    }

    @Transactional
    public StatusDto<RadiologyOrderState> acceptRejectOrder(Long id, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalRadiologyTest externalRadiologyTest = externalRadiologyTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ExternalRadiologyTestNotFound")+" "+id
                )
        );
        if (externalRadiologyTest.getState() != RadiologyOrderState.Waitting) {
            if (status.equals("Accept") || status.equals("Reject")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept" )+" or "+ messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept" )+" or "+ messages.getString("alreadyExist")

            );
        }if (status.equals("Reject") || status.equals("reject")) {
            externalRadiologyTest.setState(RadiologyOrderState.Rejected);
            externalRadiologyTestDao.updateExternalRadiologyTest(externalRadiologyTest);

            return new StatusDto<>(RadiologyOrderState.Rejected);
        }

        else {
            throw new BadRequestException(
                    messages.getString("cannotAccept" )

            );
        }

    }

    public StatusDto<RadiologyOrderState> completeOrder(Long id, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalRadiologyTest externalRadiologyTest = externalRadiologyTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ExternalRadiologyTestNotFound")+" "+id
                )
        );

        if (externalRadiologyTest.getState() != RadiologyOrderState.Accepted) {
            throw new BadRequestException(
                messages.getString("cannotAccept" )
            );
        }

        if (status.equals("completed") || status.equals("Completed")) {
            externalRadiologyTest.setState(RadiologyOrderState.Completed);
            externalRadiologyTestDao.updateExternalRadiologyTest(externalRadiologyTest);
            return new StatusDto<>(RadiologyOrderState.Completed);
        } else {
            throw new BadRequestException(
                    messages.getString("cannotAccept" )

            );
        }

    }


}
