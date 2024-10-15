package com.azu.hospital.Patients.charts.operativeNote.service;


import com.azu.hospital.Patients.charts.operativeNote.dao.OperativeNoteDao;
import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import com.azu.hospital.Patients.charts.operativeNote.request.OperativeNoteRequest;
import com.azu.hospital.Patients.charts.operativeNote.utils.OperativeNoteRequestToChart;
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

@Service
public class OperativeNoteUpdateService {

    private final OperativeNoteDao operativeNoteDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public OperativeNoteUpdateService(@Qualifier("operativeNoteJpa") OperativeNoteDao operativeNoteDao,
                                      @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn) {
        this.operativeNoteDao = operativeNoteDao;
        this.messageReturn = messageReturn;
    }


    public IdResponse updateOperativeNoteChart(
            Long chartId,
            OperativeNoteRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        OperativeNote existsChart = operativeNoteDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("OperativeNoteChartNotFound")+" "+chartId
                        )
                );


    OperativeNote newChart = OperativeNoteRequestToChart
            .getOperativeNote(request);

    compareMethodExistChartAndUpdateCart(existsChart,newChart);
    newChart.setPatient(existsChart.getPatient());

    OperativeNote savedChart = operativeNoteDao.updateExistChart(newChart);

    return new DtoForReturnIdOnly(savedChart.getId());

    }


    private static void compareMethodExistChartAndUpdateCart(
            OperativeNote existsChart,
            OperativeNote newChart)
    {
        if (newChart.getOperationName() == null){
            newChart.setOperationName(existsChart.getOperationName());
        }
        if (newChart.getOperativeNote() == null){
            newChart.setOperativeNote(existsChart.getOperativeNote());
        }
    }


}
