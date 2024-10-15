package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dao.ProcedureAnesthesiaConsentDao;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dto.ProcedureAnesthesiaConsentDto;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dto.ProcedureAnesthesiaConsentDtoMapper;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.request.ProcedureAnesthesiaConsentRequest;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.utils.ProcedureAnesthesiaConsentRequestToChart;
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

@Service("ProcedureAnesthesiaConsentService")
public class ProcedureAnesthesiaConsentService {
    private final ProcedureAnesthesiaConsentDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final ProcedureAnesthesiaConsentDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ProcedureAnesthesiaConsentService(
            @Qualifier("ProcedureAnesthesiaConsentJpaDataAccess")
            ProcedureAnesthesiaConsentDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            ProcedureAnesthesiaConsentDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<ProcedureAnesthesiaConsentDto> getAllPatientChart(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public ProcedureAnesthesiaConsentDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ProcedureAnesthesiaConsentChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("ProcedureAnesthesiaConsentChartNotFound") + " " + chartId
                        )
                );

        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            ProcedureAnesthesiaConsentRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
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

        ProcedureAnesthesiaConsentChart chart = ProcedureAnesthesiaConsentRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        ProcedureAnesthesiaConsentChart savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.PROCEDURE_ANESTHESIA_CONSENT_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }
}
