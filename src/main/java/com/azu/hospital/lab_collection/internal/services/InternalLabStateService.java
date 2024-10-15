package com.azu.hospital.lab_collection.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDtoMapper;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class InternalLabStateService extends GenericBaseService {


    private final InternalLabDao internalLabDao;

    private final InternalLabDtoMapper mapper;

    private final PatientDao patientDao;

    private final IntTestRequestDao intTestRequestDao;

    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;


    public InternalLabStateService(
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("internalLabDtoMapper") InternalLabDtoMapper mapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        super(jwtService, httpServletRequest);
        this.internalLabDao = internalLabDao;
        this.mapper = mapper;
        this.patientDao = patientDao;
        this.intTestRequestDao = intTestRequestDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }


    public ObjectNode labReceivedSample(Long intTestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("internalLabTestNotFound") + " " + intTestId
                )
        );

        if (internalLabTest.getState() != InternalLabRequestStatusEnum.GettingSample) {
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        if ((status.equals("Received") || status.equals("received"))
        ) {
            internalLabTest.setState(InternalLabRequestStatusEnum.SampleReceived);
            internalLabDao.updateInternalTest(internalLabTest);
            json.put("status", "Test Received");
        } else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }

        return json;
    }


    public ObjectNode labCompleteSample(Long intTestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("internalLabTestNotFound")
                                + " " + intTestId
                )
        );

        if (internalLabTest.getState() != InternalLabRequestStatusEnum.SampleReceived) {
            throw new IllegalStateException(
                    messages.getString("cannotAccept")
            );
        }

        if (
                (status.equals("Complete") || status.equals("complete"))
        ) {
            internalLabTest.setState(InternalLabRequestStatusEnum.Complete);
            internalLabDao.updateInternalTest(internalLabTest);
            json.put("status", "Test Complete");
        } else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }

        return json;
    }

    public ObjectNode acceptLAbTestFromDoctor(Long intTestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("internalLabTestNotFound")+ " " + intTestId
                )
        );


        if (internalLabTest.getState() != InternalLabRequestStatusEnum.WaittingAcceptFromDoctor
        ) {
            throw new IllegalStateException(
                    messages.getString("cannotAccept")
            );
        }

        if (
                (status.equals("Accept") || status.equals("accept"))
        ) {
            if (authRoles().contains("DOCTOR")) {
                internalLabTest.setState(InternalLabRequestStatusEnum.Waitting);
                internalLabTest.setAccepter(baseUserDao.findById(authId())
                        .orElseThrow(() -> new ResourceNotFoundException("User Doesn't Found")));
                internalLabDao.updateInternalTest(internalLabTest);
                json.put("status", "Test Accepted");


            } else {
                throw new IllegalStateException(
                        messages.getString("notExistingRoles") + " " + authRoles()
                );
            }
        } else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }

        return json;
    }


    public ObjectNode gettingSample(Long intTestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("internalLabTestNotFound")+ " " + intTestId
                )
        );

        if (internalLabTest.getState() != InternalLabRequestStatusEnum.Waitting) {
            throw new IllegalStateException(
                    messages.getString("cannotAccept")
            );
        }

        if (
                (status.equals("Getting") || status.equals("getting"))
        ) {
            internalLabTest.setState(InternalLabRequestStatusEnum.GettingSample);
            internalLabDao.updateInternalTest(internalLabTest);
            json.put("status", "Getting Sample");
        } else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }

        return json;
    }

    public ObjectNode CancelAcceptDoctorOrder(Long intTestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        InternalLabTest internalLabTest = internalLabDao.findById(intTestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("internalLabTestNotFound")+ " " + intTestId
                )
        );

        if (internalLabTest.getState() != InternalLabRequestStatusEnum.WaittingAcceptFromDoctor
                && internalLabTest.getState() != InternalLabRequestStatusEnum.Waitting
                && internalLabTest.getState() != InternalLabRequestStatusEnum.GettingSample
        ) {
            throw new IllegalStateException(
                    messages.getString("cannotAccept")
            );
        }

        if ((status.equals("Cancel") || status.equals("cancel"))
        ) {

            if (authRoles().contains("DOCTOR") ||
                    (!internalLabTest.getUploader().getRoles().contains("DOCTOR") && authRoles().contains("PERMANENT"))
            ) {
                internalLabTest.setState(InternalLabRequestStatusEnum.DoctorCancel);
                internalLabTest.setRejecter(baseUserDao.findById(authId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + " " + authId()
                        )));
                internalLabDao.updateInternalTest(internalLabTest);
                json.put("status", "Test Canceled");

            } else {
                throw new IllegalStateException(
                        messages.getString("cannotAccept")
                );
            }


        } else {
            json.put("status", "The status of Test Is already Change, You can't cancel it");
        }

        return json;
    }

}
