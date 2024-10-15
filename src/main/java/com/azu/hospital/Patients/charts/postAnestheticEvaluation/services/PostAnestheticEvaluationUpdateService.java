package com.azu.hospital.Patients.charts.postAnestheticEvaluation.services;

import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.dao.PostAnestheticEvaluationChartDao;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.request.PostAnestheticEvaluationRequest;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.utils.PostAnestheticEvaluationRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PostAnestheticEvaluationUpdateService")
public class PostAnestheticEvaluationUpdateService {

    private final PostAnestheticEvaluationChartDao chartDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PostAnestheticEvaluationUpdateService(
            @Qualifier("PostAnesthChartJpa")
            PostAnestheticEvaluationChartDao chartDao, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.messageReturn = messageReturn;
    }

    public IdResponse updateExistsChart(Long chartId, PostAnestheticEvaluationRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        PostAnestheticEvaluationChart existsChart = chartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PostAnestheticEvaluationChartNotFound") + " " + chartId
                        )
                );
        PostAnestheticEvaluationChart newChart = PostAnestheticEvaluationRequestToChart
                .getAnestheticEvaluationChart(request);

        compareMethodExistChartAndUpdateCart(existsChart, newChart);

        newChart.setPatient(existsChart.getPatient());

        PostAnestheticEvaluationChart update = chartDao.updateExistsChart(newChart);

        return new DtoForReturnIdOnly(update.getId());
    }

    private static void compareMethodExistChartAndUpdateCart(
            PostAnestheticEvaluationChart existsChart,
            PostAnestheticEvaluationChart newChart
    ){
        if(newChart.getAnesthesiaComplications() == null){
            newChart.setAnesthesiaComplications(existsChart.getAnesthesiaComplications());
        }
        if(newChart.getPatientStatus() == null){
            newChart.setPatientStatus(existsChart.getPatientStatus());
        }
        if(newChart.getPatientHas() == null){
            newChart.setPatientHas(existsChart.getPatientHas());
        }
        if(newChart.getPatientHasOther() == null){
            newChart.setPatientHasOther(existsChart.getPatientHasOther());
        }
        if(newChart.getPostAnesthesiaNote() == null){
            newChart.setPostAnesthesiaNote(existsChart.getPostAnesthesiaNote());
        }
        if(newChart.getAnesthesiologist() == null){
            newChart.setAnesthesiologist(existsChart.getAnesthesiologist());
        }
        if(newChart.getDate() == null){
            newChart.setDate(existsChart.getDate());
        }
        if(newChart.getTime() == null){
            newChart.setTime(existsChart.getTime());
        }
    }
}
