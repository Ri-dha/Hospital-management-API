package com.azu.hospital.operation.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.notifications.SseServer.SendNotificationDto;
import com.azu.hospital.notifications.SseServer.SseClient;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.dto.OperationDto;
import com.azu.hospital.operation.dto.OperationMapper;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.operation.request.CreateOperationRequest;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import com.azu.hospital.utils.enums.operation.OperationStates;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.azu.hospital.utils.humanResourceUtils.HumanResourceUtils.APPOINTMENT_TIME_SUFFIX;

@Service
public class OperationAddService extends GenericBaseService {


    private final PatientDao patientDao;

    private final OperationDao operationDao;
    private final BaseUserDao baseUserDao;
    private final ISseService sseService;
    private final MainSurgicalListDao mainSurgicalListDao;
    private final ExceptionsMessageReturn messageReturn;


    private final OperationMapper mapper;

    public OperationAddService(
            JwtService jwtService,
            HttpServletRequest request,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("OperationJpa") OperationDao operationDao,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            ISseService sseService, @Qualifier("MainSurgicalListJpa") MainSurgicalListDao mainSurgicalListDao,
            ExceptionsMessageReturn messageReturn,
            @Qualifier("OperationMapper") OperationMapper mapper
    ) {
        super(jwtService, request);
        this.patientDao = patientDao;
        this.operationDao = operationDao;
        this.baseUserDao = baseUserDao;
        this.sseService = sseService;
        this.mainSurgicalListDao = mainSurgicalListDao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
    }


    public void createNewOperation(Long patientId, CreateOperationRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                )
        );



        if (!patient.getIsHasMedicalHistory()) {
            throw new ResourceNotFoundException(
                    messages.getString("medicalHistory") + " " + patientId
            );
        }
        BaseUser uploader = baseUserDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("userNotFound") + " " + authId()
                )
        );

        MainSurgicalList surgicalList = mainSurgicalListDao.getSurgicalById(request.surgicalId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("surgicalListWithThisNameNotFound")+ " " + request.surgicalId()
                )
        );


        Instant date = Instant.parse(request.operationDate() + APPOINTMENT_TIME_SUFFIX);

        Operation operation = new Operation(
                request.note(),
                OperationStates.InWard,
                request.anesthetization(),
                date,
                request.triage()
        );

        patient.getPatientData().setOperation(surgicalList.getSurgicalName());
        operation.setSurgical(surgicalList);
        operation.setPatient(patient);
        operation.setUploader(uploader);

        patientDao.updatePatient(patient);
        operationDao.createNewOperation(operation);

    }
}
