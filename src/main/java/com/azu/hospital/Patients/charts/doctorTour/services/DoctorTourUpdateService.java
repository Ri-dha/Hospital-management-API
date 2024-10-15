package com.azu.hospital.Patients.charts.doctorTour.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.doctorTour.dao.DoctorTourDao;
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

import java.util.Locale;
import java.util.ResourceBundle;


@Service("DoctorTourUpdateService")
public class DoctorTourUpdateService {
    private final DoctorTourDao chartDao;
    private final PatientDao patientDao;
    private final DoctorTourDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public DoctorTourUpdateService(
            @Qualifier("DoctorTourJpaDataAccess")
            DoctorTourDao chartDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            DoctorTourDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public DtoForReturnIdOnly updateExistsChart(
            Long chartId,
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


        patient.getPatientData().setWeight(request.weight());
        patient.getPatientData().setHeight(request.height());
        patientDao.updatePatient(patient);

        DoctorTourChart existsChart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("doctorTourChartNotFound") + " " + chartId
                        )
                );

        DoctorTourChart newChart = DoctorTourRequestToChart.requestToChart(request);

        compareAndUpdateMethod(existsChart, newChart);

        newChart.setPatient(patient);

        DoctorTourChart update = chartDao.updateExistsChart(newChart);

        chartDao.updateExistsChart(newChart);
        return dtoMapper.toDtoId(update);
    }

    private void compareAndUpdateMethod(
            DoctorTourChart existsChart,
            DoctorTourChart newChart
    ) {
        if (newChart.getDate() == null) {
            newChart.setDate(existsChart.getDate());
        }
        if (newChart.getTime() == null) {
            newChart.setTime(existsChart.getTime());
        }
        if (newChart.getBmi() == null) {
            newChart.setBmi(existsChart.getBmi());
        }
        if (newChart.getMedicalDx() == null) {
            newChart.setMedicalDx(existsChart.getMedicalDx());
        }
        if (newChart.getTourDetails() == null) {
            newChart.setTourDetails(existsChart.getTourDetails());
        }
        if (newChart.getFollowUpNote() == null) {
            newChart.setFollowUpNote(existsChart.getFollowUpNote());
        }
        if (newChart.getIsMaskOn() == null) {
            newChart.setIsMaskOn(existsChart.getIsMaskOn());
        }
        if (newChart.getShift() == null) {
            newChart.setShift(existsChart.getShift());
        }
    }
}
