package com.azu.hospital.ecg.internal.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.ecg.service.IPatientEcgExpanseResultTableAddServices;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.ecg.ecgbill.dao.EcgBillDao;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.dto.EcgDto;
import com.azu.hospital.ecg.internal.dto.EcgDtoMapper;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.ecg.internal.request.InternalEcgTestRequest;
import com.azu.hospital.ecg.internal.request.InternalEcgTestRequestUpdate;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
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
public class EcgService extends GenericBaseService {

    private final PatientDao patientDao;
    private final NurseDao nurseDao;
    private final BaseUserDao userDao;
    private final EcgDao ecgDao;
    private final FileService fileService;
    private final EcgDtoMapper mapper;
    private final ISseService sseService;

    private final EcgBillDao ecgBillDao;

    private final IPatientEcgExpanseResultTableAddServices patientEcgExpanseResultTableAddServices;

    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public EcgService(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("BaseUserJpa") BaseUserDao userDao,
            @Qualifier("ecgJpa") EcgDao ecgDao,
            @Qualifier("fileService") FileService fileService,
            @Qualifier("ecgDtoMapper") EcgDtoMapper mapper,
            JwtService jwtService,
            HttpServletRequest request,
            ISseService sseService, EcgBillDao ecgBillDao,
            @Qualifier("PatientEcgExpanseResultTableAddServicesImp") IPatientEcgExpanseResultTableAddServices patientEcgExpanseResultTableAddServices, ExceptionsMessageReturn messageReturn) {
        super(jwtService, request);
        this.patientDao = patientDao;
        this.nurseDao = nurseDao;
        this.userDao = userDao;
        this.ecgDao = ecgDao;
        this.fileService = fileService;
        this.mapper = mapper;
        this.sseService = sseService;
        this.ecgBillDao = ecgBillDao;
        this.patientEcgExpanseResultTableAddServices = patientEcgExpanseResultTableAddServices;
        this.messageReturn = messageReturn;
    }


    public EcgDto createNewEcgTest(InternalEcgTestRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(request.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + request.getPatientId())
        );

        Ecg ecg = new Ecg(
                request.getNote(),
                EcgStates.Completed,
                TestDestination.Hospital);

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<File> fileList = new ArrayList<>();

            for (MultipartFile files : request.getFiles()) {
                if (!files.isEmpty()) {
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType(), "Ecg", url);
                    fileList.add(file);
                }
            }
            ecg.setFiles(fileList);
        }
        if (authRoles().contains("PERMANENT") || authRoles().contains("DOCTOR") || authRoles().contains("NURSE")) {
            ecg.setState(EcgStates.Completed);

        }

        ecg.setPatient(patient);
        ecg.setSrcUser(userDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found")));

//        EcgBill ecgBill = ecgBillDao.getExgBillById(1L).orElseThrow(
//                ()-> new ResourceNotFoundException("Ecg Bill not found"));
//        ecg.setEcgBill(ecgBill);


        ecg = ecgDao.createNewEcgTest(ecg);


        return mapper.apply(ecg);
    }


    public EcgDto createRequest(Long id, String note) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + id)
        );

        Ecg ecg = new Ecg(note, EcgStates.WaitingFromDoctor, TestDestination.Hospital);
        ecg.setSrcUser(userDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found")));
        ecg = ecgDao.createNewEcgTest(ecg);
        return mapper.apply(ecg);
    }

    public EcgDto updateEcgTest(Long id, InternalEcgTestRequestUpdate request)throws IOException {

        Ecg ecg = ecgDao.findTestById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ecg Test not found"));

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<File> fileList = new ArrayList<>();

            for (MultipartFile files : request.getFiles()) {
                if (!files.isEmpty()) {
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType(), "Ecg", url);
                    fileList.add(file);
                }
            }
            ecg.setFiles(fileList);
        }
        ecg.setNote(
                request.getNote()
        );
        ecg.setState(EcgStates.Completed);
        ecg = ecgDao.updateWitDataEcgTest(ecg);
        return mapper.apply(ecg);

    }

}
