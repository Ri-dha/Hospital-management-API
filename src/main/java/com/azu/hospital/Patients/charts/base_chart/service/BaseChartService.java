package com.azu.hospital.Patients.charts.base_chart.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.base_chart.dao.BaseChartsDao;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDtoMapper;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.all_user_sevices.user_utils.lookup.UserLookupStrategy;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BaseChartService {
    private final BaseChartsDao baseChartsDao;

    private final PatientDao patientDao;
    private final ExceptionsMessageReturn messageReturn;


    private final List<BaseChartDtoMapperInterface> allMappers;
    private final Map<Class<?>, BaseChartDtoMapperInterface> mappers = new HashMap<>();

    public BaseChartService(BaseChartsDao baseChartsDao,
                            PatientDao patientDao, ExceptionsMessageReturn messageReturn,
                            List<BaseChartDtoMapperInterface> allMappers) {
        this.baseChartsDao = baseChartsDao;
        this.patientDao = patientDao;
        this.messageReturn = messageReturn;
        this.allMappers = allMappers;
    }


    @PostConstruct
    public void initializeMapperMap() {
        for (BaseChartDtoMapperInterface mapperInstance : allMappers) {
            mappers.put(mapperInstance.getMappedClass(), mapperInstance);
        }
    }

    public Page<List<BaseChartsDto>> getAllChartsByPatientId(Long patientId, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("patientNotFound")+" "+patientId

                ));

        List<BaseCharts> baseCharts = baseChartsDao.getAllChartsByPatientId(patient.getId(), pageable).toList();


        Map<String, List<BaseChartsDto>> groupedByChartName = baseCharts.stream()
                .map(chart -> {
                    BaseChartDtoMapperInterface mapperInstance = mappers.get(chart.getClass());
                    if (mapperInstance != null) {
                        return mapperInstance.mapToDto(chart);
                    } else {
                        throw new IllegalArgumentException("No mapper found for class: " + chart.getClass().getName());
                    }
                })
                .collect(Collectors.groupingBy(BaseChartsDto::getChartName));

        List<List<BaseChartsDto>> groupedDtoLists = new ArrayList<>(groupedByChartName.values());

        Long count = baseChartsDao.countAllItems();
        return new PageImpl<>(groupedDtoLists, pageable, count);
    }


    public BaseCharts getChartById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return baseChartsDao.getChartsById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("baseChartNotFound")+" "+id
                        )
                );
    }
}
