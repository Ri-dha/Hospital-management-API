package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.PostOperativeFollowUpCallDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PostOperativeFollowUpCallChartRequest;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.utils.PostOperativeFollowUpCallRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PostOperativeFollowUpCallUpdateService")
public class PostOperativeFollowUpCallUpdateService {

    private final PostOperativeFollowUpCallDao chartDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PostOperativeFollowUpCallUpdateService(
            @Qualifier("PostOperativeFollowUpCallJpaDataAccess") PostOperativeFollowUpCallDao chartDao,
            @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.messageReturn = messageReturn;
    }

    public IdResponse updateExistsChart(Long chartId, PostOperativeFollowUpCallChartRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PostOperativeFollowUpCall existsChart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PostOperativeFollowUpCallChartNotFound") + " " + chartId
                        )
                );

        PostOperativeFollowUpCall newChart = PostOperativeFollowUpCallRequestToChart.requestToChart(request);

        compareMethodExistChartAndUpdateCart(existsChart, newChart);

        newChart.setPatient(existsChart.getPatient());

        PostOperativeFollowUpCall update = chartDao.createNewChart(newChart);

        return new DtoForReturnIdOnly(update.getId());
    }

    private static void compareMethodExistChartAndUpdateCart(
            PostOperativeFollowUpCall existsChart,
            PostOperativeFollowUpCall newChart
    ) {

        if (newChart.getDateOfCall() == null) {
            newChart.setDateOfCall(existsChart.getDateOfCall());
        }
        if (newChart.getLsmCaller() == null) {
            newChart.setLsmCaller(existsChart.getLsmCaller());
        }
        if (newChart.getNumberOfAttempts() == null) {
            newChart.setNumberOfAttempts(existsChart.getNumberOfAttempts());
        }
        if (newChart.getTime() == null) {
            newChart.setTime(existsChart.getTime());
        }
        if (newChart.getProcedure() == null) {
            newChart.setProcedure(existsChart.getProcedure());
        }
//    if (newChart.getPatientExperiencing() == null){
//      List<PatientExperiencing> patientExperiencing = new ArrayList<>(existsChart.getPatientExperiencing());
//      newChart.setPatientExperiencing(patientExperiencing);
//    }
        if (newChart.getPainLevel() == null) {
            newChart.setPainLevel(existsChart.getPainLevel());
        }

    }

}
