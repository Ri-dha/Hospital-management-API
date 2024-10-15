package com.azu.hospital.Patients.charts.preAnestheticEvaluation.services;

import com.azu.hospital.Patients.charts.physical_assment.PhysicalExam;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.dao.PreAnestheticEvaluationChartDao;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.request.PreAnestheticEvaluationChartRequest;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.utils.PreAnestheticEvaluationRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("AnestheticEvaluationUpdateService")
public class PreAnestheticEvaluationUpdateService {

    private final PreAnestheticEvaluationChartDao preAnestheticEvaluationChartDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PreAnestheticEvaluationUpdateService(
            PreAnestheticEvaluationChartDao preAnestheticEvaluationChartDao, ExceptionsMessageReturn messageReturn
    ) {
        this.preAnestheticEvaluationChartDao = preAnestheticEvaluationChartDao;
        this.messageReturn = messageReturn;
    }

    public IdResponse updateAnestheticChart(Long chartId, PreAnestheticEvaluationChartRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        PreAnestheticEvaluationChart existsChart =
                preAnestheticEvaluationChartDao
                        .findChartById(chartId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        messages.getString("PreAnestheticEvaluationChartNotFound")+ " " + chartId
                                )
                        );

        PreAnestheticEvaluationChart update =
                PreAnestheticEvaluationRequestToChart.getAnestheticEvaluationChart(request);

        compareMethodExistChartAndUpdateCart(existsChart, update);

        update.setPatient(existsChart.getPatient());

        PreAnestheticEvaluationChart newChart = preAnestheticEvaluationChartDao.updateExistsChart(update);

        return new DtoForReturnIdOnly(newChart.getId());
    }
    private static void compareMethodExistChartAndUpdateCart(PreAnestheticEvaluationChart existsChart, PreAnestheticEvaluationChart newChart){
        if(newChart.getProposedProcedure() == null){
            newChart.setProposedProcedure(existsChart.getProposedProcedure());
        }
        if(newChart.getPhysicalExam() == null){
            newChart.setPhysicalExam(existsChart.getPhysicalExam());
        }else {
            PhysicalExam newPhysicalExam = newChart.getPhysicalExam();
            PhysicalExam existsPhysicalExam = existsChart.getPhysicalExam();
            if(newPhysicalExam.getAirway() == null){
                newPhysicalExam.setAirway(existsPhysicalExam.getAirway());
            }
            if(newPhysicalExam.getCardiac() == null){
                newPhysicalExam.setCardiac(existsPhysicalExam.getCardiac());
            }
            if(newPhysicalExam.getLungs() == null){
                newPhysicalExam.setLungs(existsPhysicalExam.getLungs());
            }
            if(newPhysicalExam.getOther() == null){
                newPhysicalExam.setOther(existsPhysicalExam.getOther());
            }
        }
        if(newChart.getPlan() == null){
            newChart.setPlan(existsChart.getPlan());
        }
    }
}
