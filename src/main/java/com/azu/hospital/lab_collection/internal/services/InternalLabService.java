package com.azu.hospital.lab_collection.internal.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDto;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDtoMapper;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.azu.hospital.lab_collection.internal.request.CreateInternalLabRequest;
import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.notifications.SseServer.SendNotificationDto;
import com.azu.hospital.notifications.SseServer.SseClient;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InternalLabService extends GenericBaseService {

    private final InternalLabDao internalLabDao;

    private final InternalLabDtoMapper mapper;

    private final PatientDao patientDao;

    private final IntTestRequestDao intTestRequestDao;

    private final LabTestMainTableDao labListDao;
    private final BaseUserDao userDao;
    private final ExceptionsMessageReturn messageReturn;


    private final ISseService sseService;

    public InternalLabService(
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("internalLabDtoMapper") InternalLabDtoMapper mapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            @Qualifier("LabTestMainTableJpa") LabTestMainTableDao labListDao,
            @Qualifier("BaseUserJpa") BaseUserDao userDao, ExceptionsMessageReturn messageReturn, ISseService sseService) {
        super(jwtService, httpServletRequest);
        this.internalLabDao = internalLabDao;
        this.mapper = mapper;
        this.patientDao = patientDao;
        this.intTestRequestDao = intTestRequestDao;
        this.labListDao = labListDao;
        this.userDao = userDao;
        this.messageReturn = messageReturn;
        this.sseService = sseService;
    }

    @Transactional
    public InternalLabDto createInternalLab(Long patientId, CreateInternalLabRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                )
        );

        if (!patient.getIsHasMedicalHistory()) {
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }
        InternalLabTest internalLabTest = new InternalLabTest(
                InternalLabRequestStatusEnum.Waitting,
                request.getNote(),
                TestDestination.Hospital
        );

        if (authRoles().contains("NURSES")) {
            internalLabTest.setState(InternalLabRequestStatusEnum.WaittingAcceptFromDoctor);
        }


        InternalLabTest finalInternalLabTest = internalLabTest;
        request.getTestRequests()
                .stream()
                .map((d) -> {

                            IntTestRequest intTestRequest = new IntTestRequest();
                            intTestRequest.setSpots(d.getSpots());
                            intTestRequest.setTestNameForMainTest(labListDao.getTestFromMainTableByTestName(d.getTestName()).orElseThrow(
                                    () -> new ResourceNotFoundException(
                                            messages.getString("testNotFound") + " " + d.getTestName()
                                    )
                            ));
                            intTestRequest.setTestName(d.getTestName());
                            finalInternalLabTest.setTestRequests(List.of());
                            intTestRequestDao.createRequest(List.of(intTestRequest));
                            intTestRequest.setInternalLabTest(finalInternalLabTest);
                            return intTestRequest;

                        }
                ).collect(Collectors.toList());


        BaseUser uploader = userDao.findById(authId()).orElseThrow();

        internalLabTest.setPatient(patient);

        internalLabTest.setUploader(uploader);

        internalLabTest = internalLabDao.createNewInternalTest(internalLabTest);


        if (authRoles().contains("NURSES")) { // TODO: 1/13/2024 check for errors
            List<BaseUser> doctors = userDao.getUsersByRole(Arrays.asList("DOCTOR", "PERMANENT"));
            for (BaseUser doctor : doctors) {

                SseClient doctorClient = sseService.registeredClientById(doctor.getId());
                if (doctorClient != null) {
                    SendNotificationDto<InternalLabDto> notification = new SendNotificationDto<>(
                            "New Lab Test Request",
                            "A nurse has uploaded a new lab test request.",
                            mapper.apply(internalLabTest),
                            Instant.now()
                    );
                    sseService.sendMessage(doctorClient, DestinationEnum.LAB, notification);
                }
            }
        }


        return mapper.apply(internalLabTest);
    }


}
