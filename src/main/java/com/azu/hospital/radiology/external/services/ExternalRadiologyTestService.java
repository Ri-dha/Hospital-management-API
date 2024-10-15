package com.azu.hospital.radiology.external.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.external.dao.ExternalRadiologyTestDao;
import com.azu.hospital.radiology.external.dto.ExternalRadiologyTestDtoMapper;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.radiology.external.entity.RadiologyPatientData;
import com.azu.hospital.radiology.external.request.CreateExternalRadiologyTestRequest;
import com.azu.hospital.radiology.radiology_bill_handler.dao.RadiologyBillDao;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;


@Service
public class ExternalRadiologyTestService extends GenericBaseService {

    private final ExternalRadiologyTestDao externalRadiologyTestDao;

    private final ExternalRadiologyTestDtoMapper mapper;

    private final RadiologyBillDao radiologyBillDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ExternalRadiologyTestService(
            @Qualifier("externalRadiologyTestRepo") ExternalRadiologyTestDao externalRadiologyTestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            ExternalRadiologyTestDtoMapper mapper,
            @Qualifier("RadiologyBillJpa") RadiologyBillDao radiologyBillDao, ExceptionsMessageReturn messageReturn
    ) {
        super(jwtService, httpServletRequest);
        this.externalRadiologyTestDao = externalRadiologyTestDao;
        this.mapper = mapper;
        this.radiologyBillDao = radiologyBillDao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public DtoForReturnIdOnly createNewRadiologyTest(CreateExternalRadiologyTestRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        ExternalRadiologyTest externalRadiologyTest = new ExternalRadiologyTest(
                request.getNote(),
                request.getType(),
                RadiologyOrderState.Waitting

        );

        RadiologyPatientData patientData = new RadiologyPatientData(
                request.getPatientData().getPatientName(),
                request.getPatientData().getDoctorName(),
                request.getPatientData().getGender(),
                request.getPatientData().getAge(),
                request.getPatientData().getDx(),
                request.getPatientData().getAllergy()
        );

        externalRadiologyTest.setPatientData(patientData);

        externalRadiologyTestDao.createExternalRadiologyTest(externalRadiologyTest);

        RadiologyTypeEnum radiologyTypeEnum = externalRadiologyTest.getType();
        RadiologyBillHandler radiologyBillHandler = radiologyBillDao.findByType(radiologyTypeEnum);

        if (radiologyBillHandler != null) {
            externalRadiologyTest.setRadiologyBillHandler(radiologyBillHandler);
        }

        return mapper.toDtoForId(externalRadiologyTest);
    }


}
