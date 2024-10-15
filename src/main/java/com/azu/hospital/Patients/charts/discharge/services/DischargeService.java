package com.azu.hospital.Patients.charts.discharge.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.discharge.entity.DischargeChart;
import com.azu.hospital.Patients.charts.discharge.dao.DischargeDao;
import com.azu.hospital.Patients.charts.discharge.dto.DischargeDto;
import com.azu.hospital.Patients.charts.discharge.dto.DischargeDtoMapper;
import com.azu.hospital.Patients.charts.discharge.request.DischargeRequest;
import com.azu.hospital.Patients.charts.discharge.utils.DischargeRequestToChart;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.BillState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DischargeService {
    private final DischargeDao chartDao;
    private final PatientDao patientDao;
    private final DischargeDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    private final BedDao bedDao;

    private final FileService fileService;

    @Autowired
    public DischargeService(
            @Qualifier("DischargeJpaDataAccess") DischargeDao chartDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("dischargeDtoMapper") DischargeDtoMapper dtoMapper,
            @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn,
            @Qualifier("bedRepo") BedDao bedDao, FileService fileService
    ) {
        this.chartDao = chartDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
        this.bedDao = bedDao;

        this.fileService = fileService;
    }

    public List<DischargeDto> getAllCharts(Long patientId) {
        return chartDao
                .getAllChartsByPatientId(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }


    public DischargeDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        DischargeChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("dischargeChartNotFound")+ " "+ chartId
                        )
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            DischargeRequest request
    ) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + "  " + patientId
                        )
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("medicalHistory") + " " + patientId
            );

//        if(patient.getBillState().equals(BillState.UNPAID))
//            throw new ResourceNotFoundException(
//                    messages.getString("unpaidBills")
//            );

        DischargeChart chart = DischargeRequestToChart.requestToChart(request);
        patient.setIsArchived(true);
        Bed bed = patient.getBed();
        bed.setPatient(null);
        bedDao.updateBed(bed);
        Bed newBed = new Bed();
        newBed.setBedNumber(patient.getBed().getBedNumber());
        newBed.setIsDeleted(true);
        bedDao.updateBed(newBed);
        patient.setBed(newBed);
        patientDao.updatePatient(patient);

        chart.setPatient(patient);


        DischargeChart saved = chartDao.createNewChart(chart);

        return new DtoForReturnIdOnly(saved.getId());
    }

    public void updateExistsChart(
            Long chartId,
            Long patientId,
            DischargeRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        DischargeChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("dischargeChartNotFound") + " " + chartId
                        )
                );


        chartDao.updateExistsChart(chart);
    }

    private List<File> uploadMultiFile(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileService.saveFile(file);
                File newFile = new File(file.getContentType(), "Discharged", url);
                fileList.add(newFile);
            }
        }
        return fileList;
    }

}
