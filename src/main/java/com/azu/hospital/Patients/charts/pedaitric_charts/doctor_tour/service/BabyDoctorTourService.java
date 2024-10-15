package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.service;

import com.azu.hospital.Patients.PrematureBaby.dao.PrematureBabyDao;
import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dao.BabyDoctorTourDao;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dto.BabyDoctorTourDto;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dto.BabyDoctorTourMapper;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.entity.BabyDoctorTour;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.request.BabyDoctorTourCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BabyDoctorTourService {
    private final BabyDoctorTourDao babyDoctorTourDao;
    private final BabyDoctorTourMapper babyDoctorTourMapper;
    private final PrematureBabyDao prematureBabyDao;


    @Autowired
    public BabyDoctorTourService(
            @Qualifier("BabyDoctorTourRepo") BabyDoctorTourDao babyDoctorTourDao,
            @Qualifier("babyDoctorTourMapper") BabyDoctorTourMapper babyDoctorTourMapper,
            @Qualifier("PrematureBabyDataJpa") PrematureBabyDao prematureBabyDao) {
        this.babyDoctorTourDao = babyDoctorTourDao;
        this.babyDoctorTourMapper = babyDoctorTourMapper;
        this.prematureBabyDao = prematureBabyDao;
    }


    public BabyDoctorTourDto createNewChart(
            Long prematureBabyId,BabyDoctorTourCreateRequest request) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyById(prematureBabyId)
                .orElseThrow(() -> new RuntimeException("Premature baby not found"));

        BabyDoctorTour chart = new BabyDoctorTour();
        chart.setNote(request.note());
        chart.setMedicalDx(request.medicalDx());
        chart.setTourDetails(request.tourDetails());
        chart.setFollowUpNote(request.followUpNote());
        chart.setPrematureBaby(prematureBaby);

        babyDoctorTourDao.createNewChart(chart);
        return babyDoctorTourMapper.apply(chart);

    }


    public Page<BabyDoctorTourDto> getAllChartByPatientId(Long patientId, Pageable pageable) {
        return babyDoctorTourDao.getAllChartsByPatientId(patientId, pageable)
                .map(babyDoctorTourMapper);
    }

    public Page<BabyDoctorTourDto> getAllCharts(Pageable pageable) {
        return babyDoctorTourDao.getAllCharts(pageable)
                .map(babyDoctorTourMapper);
    }

    public BabyDoctorTourDto getChartById(Long chartId) {
        BabyDoctorTour chart = babyDoctorTourDao.findChartById(chartId)
                .orElseThrow(() -> new RuntimeException("Chart not found"));
        return babyDoctorTourMapper.apply(chart);
    }


    public BabyDoctorTourDto updateExistsChart(Long chartId,
                                               Long prematureBabyId,
                                               BabyDoctorTourCreateRequest request) {
        PrematureBaby prematureBaby = prematureBabyDao.findPrematureBabyById(prematureBabyId)
                .orElseThrow(() -> new RuntimeException("Premature baby not found"));

        BabyDoctorTour chart = babyDoctorTourDao.findChartById(chartId)
                .orElseThrow(() -> new RuntimeException("Chart not found"));

        BabyDoctorTour newChart = new BabyDoctorTour();
        newChart.setPrematureBaby(prematureBaby);

        if (request.note() == null) {
            newChart.setNote(chart.getNote());
        }else {
            newChart.setNote(request.note());
        }
        if (request.medicalDx() == null) {
            newChart.setMedicalDx(chart.getMedicalDx());
        }else  {
            newChart.setMedicalDx(request.medicalDx());
        }
        if (request.tourDetails() == null) {
            newChart.setTourDetails(chart.getTourDetails());
        }else {
            newChart.setTourDetails(request.tourDetails());
        }
        if (request.followUpNote() == null) {
            newChart.setFollowUpNote(chart.getFollowUpNote());
        }else {
            newChart.setFollowUpNote(request.followUpNote());
        }


        babyDoctorTourDao.updateExistsChart(newChart);
        return babyDoctorTourMapper.apply(newChart);
    }


}
