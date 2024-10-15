package com.azu.hospital.lab_collection.external.services;


import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.external.dao.ExternalLabDao;
import com.azu.hospital.lab_collection.external.dto.ExternalLabDtoMapper;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.dao.ExtTestRequestDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalLabStateService {


    private final ExternalLabDao externalLabDao;

    private final ExtTestRequestDao extTestRequestDao;

    private final ExternalLabDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ExternalLabStateService(
            @Qualifier("externalLabRepo") ExternalLabDao externalLabDao,
            ExtTestRequestDao extTestRequestDao,
            ExternalLabDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.externalLabDao = externalLabDao;
        this.extTestRequestDao = extTestRequestDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public ObjectNode labReceivedSample(Long extTestId, String status){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ExternalLabTest externalLabTest = externalLabDao.findById(extTestId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("labTestNotFound")+" "+extTestId
                )
        );


        if ((externalLabTest.getState() == LabRequestStatusEnum.GettingSample)
                || (status.equals("Accept") || (status.equals("accept")))

        ){
            externalLabTest.setState(LabRequestStatusEnum.SampleReceived);
            json.put("status","Test Accepted");
            externalLabDao.updateExternalTest(externalLabTest);
        }
        else{
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }

        return json;
    }



    public ObjectNode labCompleteSample(Long extTestId,String status){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ExternalLabTest externalLabTest = externalLabDao.findById(extTestId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (externalLabTest.getState() != LabRequestStatusEnum.SampleReceived &&
                externalLabTest.getState() != LabRequestStatusEnum.Cancel
        ){
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }
        if ((externalLabTest.getState() == LabRequestStatusEnum.SampleReceived)
                || (status.equals("Accept") || status.equals("accept"))

        ){
            externalLabTest.setState(LabRequestStatusEnum.Complete);
            json.put("status", "Test Accepted");
            externalLabDao.updateExternalTest(externalLabTest);
        }
        else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }
        return json;
    }


    public ObjectNode labCancelSample(Long extTestId,String status){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json  = JsonNodeFactory.instance.objectNode();
        ExternalLabTest externalLabTest = externalLabDao.findById(extTestId).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if(externalLabTest.getState()==LabRequestStatusEnum.Cancel){
            throw new IllegalStateException(
                    messages.getString("alreadyExist")
            );
        }
        if((externalLabTest.getState() == LabRequestStatusEnum.SampleReceived)
                || (status.equals("Accept") || (status.equals("accept")))
        ) {

            externalLabTest.setState(LabRequestStatusEnum.Cancel);
            json.put("status", "Request Cancel");
            externalLabDao.updateExternalTest(externalLabTest);
        }
        else {
            json.put("status", "The status of Test Is already Change, You can't Accept it");
        }
        return json;
    }

}
