package com.azu.hospital.Patients.charts.neurologicalObservation.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.NeurologicalObservationChart;
import com.azu.hospital.Patients.charts.neurologicalObservation.dao.NeurologicalObservationDao;
import com.azu.hospital.Patients.charts.neurologicalObservation.dto.NeurologicalObservationDto;
import com.azu.hospital.Patients.charts.neurologicalObservation.dto.NeurologicalObservationDtoMapper;
import com.azu.hospital.Patients.charts.neurologicalObservation.request.NeurologicalObservationRequest;
import com.azu.hospital.Patients.charts.neurologicalObservation.utils.NeurologicalObservationRequestToChart;
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

@Service("NeurologicalObservationService")
public class NeurologicalObservationService {
    private final NeurologicalObservationDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final NeurologicalObservationDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired

    public NeurologicalObservationService(
            @Qualifier("NeurologicalObservationJpaDataAccess")
            NeurologicalObservationDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            NeurologicalObservationDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<NeurologicalObservationDto> getAllCharts(Long patientId) {
        return chartDao.getAllChartsByPatientId(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public NeurologicalObservationDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        NeurologicalObservationChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("NeurologicalObservationChartNotFound") + " " + chartId)
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            NeurologicalObservationRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")+ " " + patientId)
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("patientHasNoMedicalHistory") + " " + patientId
            );

        AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
                .orElseGet(
                        () -> {
                            AllPatientChart c = new AllPatientChart();
                            c.setPatient(patient);
                            return allPatientChartsDao.addNewChart(c);
                        }
                );

        NeurologicalObservationChart chart = NeurologicalObservationRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        NeurologicalObservationChart savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.NEUROLOGICAL_OBSERVATION_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }
}
