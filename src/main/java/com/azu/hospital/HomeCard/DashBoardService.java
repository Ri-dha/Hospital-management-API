package com.azu.hospital.HomeCard;

import com.azu.hospital.HomeCard.dto.DashBoardDto;
import com.azu.hospital.HomeCard.dto.DashBoardOperationAnalysisDto;
import com.azu.hospital.HomeCard.dto.DashBoardOperationDto;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashBoardService {
    private final OperationDao operationDao;

    private final PatientDao patientDao;

    private final BaseUserDao baseUserDao;


    @Autowired
    public DashBoardService(OperationDao operationDao, PatientDao patientDao, BaseUserDao baseUserDao) {
        this.operationDao = operationDao;
        this.patientDao = patientDao;
        this.baseUserDao = baseUserDao;
    }


    public DashBoardDto getDashBoard(){
        Long totalOperation = operationDao.countAll();
        Long totalPatient = patientDao.countAll();
        Long totalStaff = baseUserDao.countAll();

        return new DashBoardDto(
                totalPatient,
                totalOperation,
                totalStaff
        );

    }

    public DashBoardOperationDto getDashBoardOperation(String date){

        Instant month = Instant.parse(date + "-01T00:00:00Z");

        Long totalWaiting = operationDao.countAllByStateAndMonth(OperationStates.Waiting, month);
        Long totalInOperation = operationDao.countAllByStateAndMonth(OperationStates.InOperation, month);
        Long totalRecovery = operationDao.countAllByStateAndMonth(OperationStates.Recovery, month);

        return new DashBoardOperationDto(
                totalWaiting,
                totalInOperation,
                totalRecovery
        );
    }

    public Map<LocalDate, DashBoardOperationAnalysisDto.DayData> getDashBoardOperationAnalysis() {
        Map<LocalDate, DashBoardOperationAnalysisDto.DayData> dailyCounts = new HashMap<>();

        Instant now = Instant.now();
        for (int i = 0; i < 7; i++) {
            Instant currentDay = now.minus(i, ChronoUnit.DAYS);
            LocalDate date = LocalDate.ofInstant(currentDay, ZoneId.systemDefault());

            Long totalAccepted = operationDao.countAllByListOfStatesAndOperationDate(
                    List.of(OperationStates.Recovery,
                            OperationStates.SentToWard,
                            OperationStates.PatientReceived,
                            OperationStates.WaitingForWard), currentDay);
            Long totalCanceled = operationDao.countAllByStateAndOperationDate(OperationStates.Canceled, currentDay);

            dailyCounts.put(date, new DashBoardOperationAnalysisDto.DayData(totalAccepted, totalCanceled));
        }

        return dailyCounts;
    }


}
