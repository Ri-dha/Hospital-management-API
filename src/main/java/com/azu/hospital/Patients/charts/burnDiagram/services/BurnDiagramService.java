package com.azu.hospital.Patients.charts.burnDiagram.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.burnDiagram.dao.BurnDiagramDao;
import com.azu.hospital.Patients.charts.burnDiagram.dto.BurnDiagramDto;
import com.azu.hospital.Patients.charts.burnDiagram.dto.BurnDiagramDtoMapper;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import com.azu.hospital.Patients.charts.burnDiagram.request.BurnDiagramRequest;
import com.azu.hospital.Patients.charts.burnDiagram.utils.BurnDiagramRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("BurnDiagramService")
public class BurnDiagramService {
    private final BurnDiagramDao chartDao;
    private final PatientDao patientDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final BurnDiagramDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public BurnDiagramService(
            @Qualifier("BurnDiagramJpaDataAccess")
            BurnDiagramDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            PatientDao patientDao, BurnDiagramDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<BurnDiagramDto> getPatientCharts(Long patientId) {
        List<BurnDiagramDto> charts = chartDao.getAllChartsByPatientId(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
        return charts;
    }

    public BurnDiagramDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        BurnDiagramChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("BurnDiagramChartNotFound")+" "+chartId
                        )
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            BurnDiagramRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")+" "+patientId
                        )
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("patientHasNoMedicalHistory")+" "+patientId
            );

        AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
                .orElseGet(
                        () -> {
                            AllPatientChart c = new AllPatientChart();
                            c.setPatient(patient);
                            return allPatientChartsDao.addNewChart(c);
                        }
                );

        BurnDiagramChart chart = BurnDiagramRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        BurnDiagramChart savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.BURN_DIAGRAM_CHART);
        allPatientChartsDao.updateCharts(allCharts);


        return new DtoForReturnIdOnly(savedChart.getId());
    }
}