package com.azu.hospital.radiology.internal.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class InternalRadiologyTestStateService extends GenericBaseService {

    private final InternalRadiologyTestDao internalRadiologyTestDao;
    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;



    public InternalRadiologyTestStateService(
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            JwtService jwtService,
            HttpServletRequest request,
            BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        super(jwtService, request);
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }


    public StatusDto<RadiologyOrderState> acceptRejectOrder(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalRadiologyTest internalRadiologyTest = internalRadiologyTestDao.getInternalRadiologyTestById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("InternalRadiologyTestNotFound")+" "+id
                )
        );

        if ((internalRadiologyTest.getState() != RadiologyOrderState.Waitting)) {
            if (state.equals("accept") || state.equals("Accept")) {
                throw new BadRequestException("Can't reject This Test( Already accept or not in waitting state )");
            }
            throw new BadRequestException("Can't Accept This Test ( Already Accepted or not in waitting state )");
        }

       if (state.equals("accept") || state.equals("Accept")) {
                internalRadiologyTest.setState(RadiologyOrderState.Accepted);
                internalRadiologyTest.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                        ()-> new ResourceNotFoundException("User not found")));
                internalRadiologyTestDao.updateInternalRadiologyTest(internalRadiologyTest);
           return new StatusDto<>(RadiologyOrderState.Accepted);
       }

            throw new BadRequestException("Invalid State (it should be Accept or Reject )");
        }
    }
