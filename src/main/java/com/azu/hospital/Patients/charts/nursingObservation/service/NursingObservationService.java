package com.azu.hospital.Patients.charts.nursingObservation.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.nursingObservation.dao.NursingObservationDao;
import com.azu.hospital.Patients.charts.nursingObservation.dao.Observation.ObservationDao;
import com.azu.hospital.Patients.charts.nursingObservation.dto.NursingObservationDto;
import com.azu.hospital.Patients.charts.nursingObservation.dto.NursingObservationDtoMapper;
import com.azu.hospital.Patients.charts.nursingObservation.dto.ObservationTimeDtoMapper;
import com.azu.hospital.Patients.charts.nursingObservation.entity.NursingObservation;
import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;
import com.azu.hospital.Patients.charts.nursingObservation.request.NursingObservationRequest;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class NursingObservationService extends GenericBaseService {
    private final NursingObservationDao nursingObservationDao;
    private final NursingObservationDtoMapper nursingObservationDtoMapper;
    private final ObservationDao observationDao;
    private final ObservationTimeDtoMapper observationTimeDtoMapper;
    private final PatientDao patientDao;
    private final NurseDao nurseDao;
    private final BaseUserDao baseUserDao;

    private final DrugRequestHandlerDao drugRequestHandlerDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public NursingObservationService(
            @Qualifier("NursingObservationJpaData") NursingObservationDao nursingObservationDao,
            @Qualifier("nursingObservationDtoMapper") NursingObservationDtoMapper nursingObservationDtoMapper,
            @Qualifier("ObservationJpa") ObservationDao observationDao,
            @Qualifier("observationTimeDtoMapper") ObservationTimeDtoMapper observationTimeDtoMapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("DrugRequestHandlerJpa") DrugRequestHandlerDao drugRequestHandlerDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest, BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        super(jwtService, httpServletRequest);
        this.nursingObservationDao = nursingObservationDao;
        this.nursingObservationDtoMapper = nursingObservationDtoMapper;
        this.observationDao = observationDao;
        this.observationTimeDtoMapper = observationTimeDtoMapper;
        this.patientDao = patientDao;
        this.nurseDao = nurseDao;
        this.drugRequestHandlerDao = drugRequestHandlerDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }


    public void addNursingObservation(Long patientId, NursingObservationRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound")+" "+patientId

        ));
        BaseUser nurse = baseUserDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("userNotFound")+" "+authId()
                )
        );

        NursingObservation nursingObservation = new NursingObservation(
                request.note()
        );

        List<ObservationTime> observationTimeList = request.observationTimes().stream()
                .map(d -> {
                    ObservationTime observationTime = new ObservationTime();
                    observationTime.setTime(d.time());
                    observationTime.setTimeStatus(d.timeStatus());
                    observationTime.setRoa(d.roa());
                    observationTime.setDrugs(drugRequestHandlerDao.getRequestById(d.requestId()).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("drugRequestNotFound")+d.requestId()
                    )));
                    observationTime.setNursingObservation(nursingObservation);
                    return observationTime;
                })
                .collect(Collectors.toList());

        nursingObservation.setObservationTimes(observationTimeList);

        nursingObservation.setPatient(patient);
        nursingObservation.setNurse(nurse);
        nursingObservationDao.createNewChart(nursingObservation);

    }



    public List<NursingObservationDto> getAllPatientCharts(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound")+" "+patientId
                )
        );
        return nursingObservationDao.getAllChartsByPatientId(patientId)
                .stream()
                .map(nursingObservationDtoMapper::chartToDto)
                .toList();
    }


    public NursingObservationDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return nursingObservationDao.findChartById(chartId)
                .map(nursingObservationDtoMapper::chartToDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("NursingObservationChartNotFound")+chartId
                        )
                );
    }





}