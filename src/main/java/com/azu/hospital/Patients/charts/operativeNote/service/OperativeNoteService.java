package com.azu.hospital.Patients.charts.operativeNote.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.operativeNote.dao.OperativeNoteDao;
import com.azu.hospital.Patients.charts.operativeNote.dto.OperativeNoteDto;
import com.azu.hospital.Patients.charts.operativeNote.dto.OperativeNoteDtoMapper;
import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import com.azu.hospital.Patients.charts.operativeNote.request.OperativeNoteRequest;
import com.azu.hospital.Patients.charts.operativeNote.utils.OperativeNoteRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OperativeNoteService {

    private final OperativeNoteDao operativeNoteDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final ExceptionsMessageReturn messageReturn;

    private final PatientDao patientDao;

    private final OperativeNoteDtoMapper operativeNoteDtoMapper;

    @Autowired
    public OperativeNoteService(OperativeNoteDao operativeNoteDao, AllPatientChartsDao allPatientChartsDao, ExceptionsMessageReturn messageReturn, PatientDao patientDao, OperativeNoteDtoMapper operativeNoteDtoMapper) {
        this.operativeNoteDao = operativeNoteDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.messageReturn = messageReturn;
        this.patientDao = patientDao;
        this.operativeNoteDtoMapper = operativeNoteDtoMapper;
    }

    public List<OperativeNoteDto> getAllChart(
            Long patientId
    ) {
        return operativeNoteDao.getAllCharts(patientId)
                .stream()
                .map(operativeNoteDtoMapper::chartDto)
                .toList();
    }

    public IdResponse addOperativeNoteChart(
            Long patientId,
            OperativeNoteRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("patientNotFound")+" "+patientId
                ));

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(messages.getString("medicalHistoryNotFound")+" "+patientId);

        AllPatientChart allChart = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
                .orElseGet(
                        () ->
                        {
                            AllPatientChart c = new AllPatientChart();
                            c.setPatient(patient);
                            return allPatientChartsDao.addNewChart(c);
                        }
                );
        OperativeNote operativeNote = OperativeNoteRequestToChart
                .getOperativeNote(request);

        operativeNote.setPatient(patient);

        OperativeNote newChart = operativeNoteDao.createNewChart(operativeNote);
        allChart.setChartType(ChartTypes.OPERATIVE_NOTE_CHART);

        allPatientChartsDao.updateCharts(allChart);

        return new DtoForReturnIdOnly(newChart.getId());

    }


    public OperativeNoteDto getChartById(
            Long chartId
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return operativeNoteDao.findChartById(chartId)
                .map(operativeNoteDtoMapper::chartDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("chartNotFound")+" "+chartId
                        )
                );
    }

}
