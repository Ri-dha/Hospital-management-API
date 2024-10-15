package com.azu.hospital.ultrasound.internal.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.notifications.SseServer.SendNotificationDto;
import com.azu.hospital.notifications.SseServer.SseClient;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.ultrasound.internal.request.CreateInternalUltrasoundTestRequest;
import com.azu.hospital.ultrasound.ultrasoundBill.dao.UltrasoundBillDao;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
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
public class InternalUltrasoundTestService extends GenericBaseService {

    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final PatientDao patientDao;
    private final InternalUltrasoundTestDtoMapper mapper;
    private final UltrasoundBillDao ultrasoundBillDao;
    private final ISseService sseService;
    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;

    public InternalUltrasoundTestService(
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("internalUltrasoundTestDtoMapper") InternalUltrasoundTestDtoMapper mapper,
            JwtService jwtService ,
            HttpServletRequest request, UltrasoundBillDao ultrasoundBillDao,
            ISseService sseService, BaseUserDao userDao, ExceptionsMessageReturn messageReturn) {
        super(jwtService,request);
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.ultrasoundBillDao = ultrasoundBillDao;
        this.sseService = sseService;
        this.userDao = userDao;
        this.messageReturn = messageReturn;
    }



    @Transactional
    public InternalUltrasoundTestDto createNewUltrasoundTest(CreateInternalUltrasoundTestRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());


        Patient patient = patientDao.getPatientById(request.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


        InternalUltrasoundTest internalUltrasoundTest = new InternalUltrasoundTest(
                request.getNote() ,
                request.getType() ,
                UltrasoundOrderState.Waitting ,
                TestDestination.Hospital
        );
        if(authRoles().contains("PERMANENT")|| authRoles().contains("DOCTOR")){
            internalUltrasoundTest.setState(UltrasoundOrderState.Waitting);
        } else if (authRoles().contains("NURSES")) {
            internalUltrasoundTest.setState(UltrasoundOrderState.WaittingForDoctorAccept);
        }

        internalUltrasoundTest.setPatient(patient);

        internalUltrasoundTest.setUploader(userDao.findById(authId()).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )));

        internalUltrasoundTest = internalUltrasoundTestDao.createInternalUltrasoundTest(internalUltrasoundTest);

        UltrasoundTypeEnum typeEnum = internalUltrasoundTest.getType();
        UltrasoundBill ultrasoundBill=  ultrasoundBillDao.findByType(typeEnum);

        if (ultrasoundBill != null) {
            internalUltrasoundTest.setUltrasoundBill(ultrasoundBill);
        }else {
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }

        return mapper.apply(internalUltrasoundTest);
    }



}
