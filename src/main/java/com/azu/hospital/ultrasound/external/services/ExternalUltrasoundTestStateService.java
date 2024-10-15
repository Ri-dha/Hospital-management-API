package com.azu.hospital.ultrasound.external.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.external.dao.ExternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.external.dto.ExternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalUltrasoundTestStateService {

    private final ExceptionsMessageReturn messageReturn;

    private final ExternalUltrasoundTestDao externalUltrasoundTestDao;

    private final PatientDao patientDao;
    private final ExternalUltrasoundTestDtoMapper mapper;

    public ExternalUltrasoundTestStateService(
            ExceptionsMessageReturn messageReturn, @Qualifier("externalUltrasoundTestRepo") ExternalUltrasoundTestDao externalUltrasoundTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            ExternalUltrasoundTestDtoMapper mapper
    ) {
        this.messageReturn = messageReturn;
        this.externalUltrasoundTestDao = externalUltrasoundTestDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
    }




    @Transactional
    public StatusDto<UltrasoundOrderState> acceptRejectOrder(Long id , String status){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalUltrasoundTest externalUltrasoundTest = externalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (externalUltrasoundTest.getState() != UltrasoundOrderState.Waitting){
            if (status.equals("reject") || status.equals("Reject")){
                throw new BadRequestException(
                        messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")
                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")

            );
        }

        if (status.equals("reject") || status.equals("Reject")){
            externalUltrasoundTest.setState(UltrasoundOrderState.Rejected);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.Rejected);}
        else if (status.equals("accept") || status.equals("Accept")){
            externalUltrasoundTest.setState(UltrasoundOrderState.Accepted);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.Accepted);
        }
        else {
            throw new BadRequestException("cannotAccept");
        }

    }


    public StatusDto<UltrasoundOrderState> cancelAcceptDoctorOrder(Long id , String status){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalUltrasoundTest externalUltrasoundTest = externalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (externalUltrasoundTest.getState() != UltrasoundOrderState.WaittingForDoctorAccept){
            if (status.equals("cancel") || status.equals("Cancel")){
                throw new BadRequestException(
                        messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept" )
            );
        }

        if (status.equals("cancel") || status.equals("Cancel")){
            externalUltrasoundTest.setState(UltrasoundOrderState.DoctorCancel);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.DoctorCancel);
        }
        else if (status.equals("waitting") || status.equals("Waitting")){
            externalUltrasoundTest.setState(UltrasoundOrderState.Waitting);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.Waitting);
        }
        else {
            throw new BadRequestException("cannotAccept");
        }



    }


    public StatusDto<UltrasoundOrderState> receivedOrder(Long id, String status){

        ExternalUltrasoundTest externalUltrasoundTest = externalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        if (externalUltrasoundTest.getState() != UltrasoundOrderState.Accepted){
            throw new BadRequestException("Can't Receive This Test( Already Received or not in Accepted state )");
        }

            if (status.equals("received") || status.equals("Received")){
            externalUltrasoundTest.setState(UltrasoundOrderState.Received);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.Received);
        }
        else {
            throw new BadRequestException("Invalid Status");
        }

    }

    public StatusDto<UltrasoundOrderState> completeOrder(Long id, String status){

        ExternalUltrasoundTest externalUltrasoundTest = externalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        if (externalUltrasoundTest.getState() != UltrasoundOrderState.Received){
            throw new BadRequestException("Can't Complete This Test( Already Completed or not in Received state )");
        }

        if (status.equals("completed") || status.equals("Completed")){
            externalUltrasoundTest.setState(UltrasoundOrderState.Completed);
            externalUltrasoundTestDao.updateExternalUltrasoundTest(externalUltrasoundTest);
            return new StatusDto<>(UltrasoundOrderState.Completed);
        }
        else {
            throw new BadRequestException("Invalid Status");
        }




    }

}
