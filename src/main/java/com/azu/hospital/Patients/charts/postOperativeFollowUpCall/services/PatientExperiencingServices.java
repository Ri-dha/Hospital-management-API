package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.PatientExperiencingDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.PostOperativeFollowUpCallDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PatientExperiencingDto;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PatientExperiencingDtoMapper;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PatientExperiencingRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PatientExperiencingServices")
public class PatientExperiencingServices {
    private final PatientExperiencingDao experiencingDao;
    private final PostOperativeFollowUpCallDao chartDao;
    private final PatientExperiencingDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public PatientExperiencingServices(
            @Qualifier("PatientExperiencingJpaDataAccess")
            PatientExperiencingDao experiencingDao,
            @Qualifier("PostOperativeFollowUpCallJpaDataAccess")
            PostOperativeFollowUpCallDao chartDao,
            PatientExperiencingDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.experiencingDao = experiencingDao;
        this.chartDao = chartDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<PatientExperiencingDto> getAllChartExperiencing(Long chartId) {
        return experiencingDao.getAllExperiencingCharts(chartId)
                .stream()
                .map(dtoMapper::entityToDto)
                .toList();
    }

    public void createNewChartPatientExperiencing(
            Long chartId,
            List<PatientExperiencingRequest> requests
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PostOperativeFollowUpCall chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + " " + chartId)
                );

        List<PatientExperiencing> patientExperiencings = new ArrayList<>();

        for (PatientExperiencingRequest item : requests) {
            PatientExperiencing experiencing = new PatientExperiencing(
                    item.experience(),
                    item.isChecked(),
                    item.note()
            );
            experiencing.setPostOperativeFollowUpCall(chart);

            patientExperiencings.add(experiencing);
        }

        experiencingDao.createNewExperiencingChart(patientExperiencings);
    }

//  public void updatePatientExperiencing(
//          Long experiencingId,
//          PatientExperiencingRequest request
//  ){
//    PatientExperiencing patientExperiencing = experiencingDao
//            .findExperiencingChartById(experiencingId)
//            .orElseThrow(
//                    () -> new ResourceNotFoundException(
//                            "Resource Not Found"
//                    )
//            );
//
//    PatientExperiencing newPatientExperiencing = new PatientExperiencing(
//            request.experience(),
//            request.isChecked(),
//            request.note()
//    );
//    newPatientExperiencing
//            .setPostOperativeFollowUpCall(
//                    patientExperiencing
//                            .getPostOperativeFollowUpCall()
//            );
//
//    if(newPatientExperiencing.getIsChecked() == null){
//      newPatientExperiencing.setIsChecked(patientExperiencing.getIsChecked());
//    }
//    if(newPatientExperiencing.getNote() == null){
//      newPatientExperiencing.setNote(patientExperiencing.getNote());
//    }
//    if(newPatientExperiencing.getExperience() == null){
//      newPatientExperiencing.setExperience(patientExperiencing.getExperience());
//    }
//    experiencingDao.createNewExperiencingChart(List.of(newPatientExperiencing));
//  }
//}
}