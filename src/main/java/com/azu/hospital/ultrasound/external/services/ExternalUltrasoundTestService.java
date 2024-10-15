package com.azu.hospital.ultrasound.external.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.external.dao.ExternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.external.dto.ExternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.external.dto.ExternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.ultrasound.external.entity.UltrasoundPatientData;
import com.azu.hospital.ultrasound.external.request.CreateExternalUltrasoundTestRequest;
import com.azu.hospital.ultrasound.ultrasoundBill.dao.UltrasoundBillDao;
import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalUltrasoundTestService {

    private final ExternalUltrasoundTestDao externalUltrasoundTestDao;

    private final UltrasoundBillDao ultrasoundBillDao;
    private final PatientDao patientDao;
    private final ExternalUltrasoundTestDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    public ExternalUltrasoundTestService(
            @Qualifier("externalUltrasoundTestRepo") ExternalUltrasoundTestDao externalUltrasoundTestDao, UltrasoundBillDao ultrasoundBillDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            ExternalUltrasoundTestDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.externalUltrasoundTestDao = externalUltrasoundTestDao;
        this.ultrasoundBillDao = ultrasoundBillDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public DtoForReturnIdOnly createNewUltrasoundTest(CreateExternalUltrasoundTestRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        ExternalUltrasoundTest externalUltrasoundTest = new ExternalUltrasoundTest(
                request.getNote(),
                request.getUltrasoundType(),
                UltrasoundOrderState.Waitting
        );


        UltrasoundPatientData patientData = new UltrasoundPatientData(
                request.getPatientData().getPatientName(),
                request.getPatientData().getDoctorName(),
                request.getPatientData().getGender(),
                request.getPatientData().getWeight(),
                request.getPatientData().getHeight(),
                request.getPatientData().getAge()
        );

        externalUltrasoundTest.setPatientData(patientData);
        externalUltrasoundTestDao.createExternalUltrasoundTest(externalUltrasoundTest);

        UltrasoundTypeEnum ultrasoundTypeEnum = request.getUltrasoundType();
        UltrasoundBill ultrasoundBill = ultrasoundBillDao.findByType(ultrasoundTypeEnum);

        if (ultrasoundBill != null) {
            externalUltrasoundTest.setUltrasoundBill(ultrasoundBill);
        } else {
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }


        return mapper.toDtoForId(externalUltrasoundTest);
    }


}
