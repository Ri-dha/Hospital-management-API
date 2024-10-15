package com.azu.hospital.Patients.charts.patientAssessment.services;


import com.azu.hospital.Patients.charts.patientAssessment.request.PatientAssessmentChartRequest;
import com.azu.hospital.Patients.charts.patientAssessment.utils.PatientAssessmentRequestToChart;
import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;
import com.azu.hospital.Patients.charts.patientAssessment.dao.PatientAssessmentChartDao;
import com.azu.hospital.Patients.charts.physical_assment.IVSite;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PatientAssessmentUpdateService")
public class PatientAssessmentUpdateService {
    private final ExceptionsMessageReturn messageReturn;

    private final PatientAssessmentChartDao chartDao;


    public PatientAssessmentUpdateService(
            ExceptionsMessageReturn messageReturn, @Qualifier("PatientAssessmentJpaDataAccess")
            PatientAssessmentChartDao chartDao
    ) {
        this.messageReturn = messageReturn;
        this.chartDao = chartDao;
    }
    public IdResponse updateExistsChart(Long chartId, PatientAssessmentChartRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        PatientAssessmentChart existsChart = chartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PatientAssessmentChartNotFound") + " " + chartId
                        )
                );
        PatientAssessmentChart newChart = PatientAssessmentRequestToChart
                .requestToChart(request);

        compareMethodExistChartAndUpdateCart(existsChart, newChart);

        newChart.setPatient(existsChart.getPatient());

        PatientAssessmentChart update = chartDao.updateExistsChart(newChart);

        return new DtoForReturnIdOnly(update.getId());
    }

    private static void compareMethodExistChartAndUpdateCart(PatientAssessmentChart existsChart, PatientAssessmentChart newChart){
        if(newChart.getDiagnosis() == null){
            newChart.setDiagnosis(existsChart.getDiagnosis());
        }
        if(newChart.getProcedures() == null){
            newChart.setProcedures(existsChart.getProcedures());
        }
        if(newChart.getSkinCondition() == null){
            newChart.setSkinCondition(existsChart.getSkinCondition());
        }
        if (newChart.getPhysicalLimitations() == null){
            newChart.setPhysicalLimitations(existsChart.getPhysicalLimitations());
        }
        if (newChart.getLoc() == null){
            newChart.setLoc(existsChart.getLoc());
        }
        if(newChart.getIvSite() == null){
            newChart.setIvSite(existsChart.getIvSite());
        }else{
            IVSite newIvSite = newChart.getIvSite();
            IVSite existsIvSite = existsChart.getIvSite();
            if(newIvSite.getFluid() == null){
                newIvSite.setFluid(existsIvSite.getFluid());
            }
            if(newIvSite.getSiteType() == null){
                newIvSite.setSiteType(existsIvSite.getSiteType());
            }
            if(newIvSite.getSiteLocation() == null){
                newIvSite.setSiteLocation(existsIvSite.getSiteLocation());
            }
        }
    }
}
