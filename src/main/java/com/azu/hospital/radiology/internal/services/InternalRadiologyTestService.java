package com.azu.hospital.radiology.internal.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.notifications.SseServer.SendNotificationDto;
import com.azu.hospital.notifications.SseServer.SseClient;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDtoMapper;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.radiology.internal.request.CreateInternalRadiologyTestRequest;
import com.azu.hospital.radiology.radiology_bill_handler.dao.RadiologyBillDao;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class InternalRadiologyTestService extends GenericBaseService {

    private final InternalRadiologyTestDao internalRadiologyTestDao;

    private final PatientDao patientDao;
    private final InternalRadiologyTestDtoMapper mapper;
    private final ISseService sseService;

    private final RadiologyBillDao radiologyBillDao;
    private final ExceptionsMessageReturn messageReturn;
    private final BaseUserDao userDao;

    public InternalRadiologyTestService(
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            JwtService jwtService,
            HttpServletRequest request,
            InternalRadiologyTestDtoMapper mapper,
            ISseService sseService,
            @Qualifier("RadiologyBillJpa") RadiologyBillDao radiologyBillDao, ExceptionsMessageReturn messageReturn, BaseUserDao userDao) {
        super(jwtService, request);
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.sseService = sseService;
        this.radiologyBillDao = radiologyBillDao;
        this.messageReturn = messageReturn;
        this.userDao = userDao;
    }


    @Transactional
    public InternalRadiologyTestDto createNewRadiologyTest(CreateInternalRadiologyTestRequest request) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(request.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + request.getPatientId()
                )
        );


        InternalRadiologyTest internalRadiologyTest = new InternalRadiologyTest(
                request.getNote(),
                request.getType(),
                RadiologyOrderState.Waitting,
                TestDestination.Hospital
        );


        internalRadiologyTest.setState(RadiologyOrderState.Waitting);

        internalRadiologyTest.setPatient(patient);

        internalRadiologyTest.setUploader(userDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("userNotFound") + " " + authId()
                )
        ));

        internalRadiologyTest = internalRadiologyTestDao.createInternalRadiologyTest(internalRadiologyTest);

        RadiologyTypeEnum radiologyTypeEnum = internalRadiologyTest.getType();
        RadiologyBillHandler radiologyBillHandler = radiologyBillDao.findByType(radiologyTypeEnum);

        if (radiologyBillHandler != null) {
            internalRadiologyTest.setRadiologyBillHandler(radiologyBillHandler);
        } else {
            throw new ResourceNotFoundException(
                    messages.getString("radiologyBillHandlerNotFound") + " " + radiologyTypeEnum
            );
        }

        return mapper.apply(internalRadiologyTest);
    }


}
