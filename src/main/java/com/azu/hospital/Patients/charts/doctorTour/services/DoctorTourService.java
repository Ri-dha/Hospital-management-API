package com.azu.hospital.Patients.charts.doctorTour.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.doctorTour.dao.DoctorTourDao;
import com.azu.hospital.Patients.charts.doctorTour.dto.DoctorTourDto;
import com.azu.hospital.Patients.charts.doctorTour.dto.DoctorTourDtoMapper;
import com.azu.hospital.Patients.charts.doctorTour.entity.DoctorTourChart;
import com.azu.hospital.Patients.charts.doctorTour.request.DoctorTourRequest;
import com.azu.hospital.Patients.charts.doctorTour.utils.DoctorTourRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("DoctorTourService")
public class DoctorTourService {
    private final DoctorTourDao chartDao;
    private final PatientDao patientDao;
    private final DoctorTourDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public DoctorTourService(
            @Qualifier("DoctorTourJpaDataAccess")
            DoctorTourDao chartDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            DoctorTourDtoMapper dtoMapper,
            ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<DoctorTourDto> getAllChartByPatientId(Long patientId) {
        return chartDao.getAllChartsByPatientId(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public DoctorTourDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        DoctorTourChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("doctorTourChartNotFound") + " " + chartId
                        )
                );

        return dtoMapper.chartToDto(chart);
    }

    public DtoForReturnIdOnly createNewChart(
            Long patientId,
            DoctorTourRequest request
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

        DoctorTourChart chart = DoctorTourRequestToChart
                .requestToChart(request);

        chart.setPatient(patient);
        patient.setBaseCharts(List.of(chart));
        DoctorTourChart newChart = chartDao.createNewChart(chart);

        return dtoMapper.toDtoId(newChart);
    }
}
