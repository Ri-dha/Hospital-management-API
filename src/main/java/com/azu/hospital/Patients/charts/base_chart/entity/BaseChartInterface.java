package com.azu.hospital.Patients.charts.base_chart.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;

import java.util.List;

public interface BaseChartInterface {

    Long getId();

    Patient getPatient();

    void setPatient(Patient patient);

    BaseUser getBaseUser();

    void setBaseUser(List<BaseUser> baseUser);
}
