package com.azu.hospital.Patients.Patient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.dto.PatientAllDto;
import com.azu.hospital.Patients.Patient.dto.PatientAllDtoMapper;
import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.Patient.dto.PatientDtoMapper;
import com.azu.hospital.Patients.Patient.request.GetAllPatientRequest;
import com.azu.hospital.Patients.entryTable.dao.EntryTableDao;
import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import com.azu.hospital.accounting.insurance.dao.InsuranceDao;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentDao;
import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.BillState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.Image;
import com.azu.hospital.utils.image.ImageService;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientGetService {
    private final PatientDao patientDao;

    private final EntryTableDao entryTableDao;

    private final PatientDtoMapper mapper;
    private final PatientAllDtoMapper mapperAll;
    private final DoctorDao doctorDao;
    private final WardDao wardDao;

    private final ImageService imageService;

    private final FileService fileService;

    private final ExceptionsMessageReturn messageReturn;


    private final IPatientWalletService patientWalletService;

    public PatientGetService(PatientDao patientDao , EntryTableDao entryTableDao,
                             PatientDtoMapper mapper, PatientAllDtoMapper mapperAll,
                             DoctorDao doctorDao, WardDao wardDao, ImageService imageService,
                             FileService fileService, ExceptionsMessageReturn messageReturn, IPatientWalletService patientWalletService) {
        this.patientDao = patientDao;
        this.entryTableDao = entryTableDao;
        this.mapper = mapper;
        this.mapperAll = mapperAll;
        this.doctorDao = doctorDao;
        this.wardDao = wardDao;
        this.imageService = imageService;
        this.fileService = fileService;
        this.messageReturn = messageReturn;
        this.patientWalletService = patientWalletService;
    }


    public PatientDto getPatientById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return patientDao.getPatientById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")
                        )
                );
    }

    public PatientAllDto getPatientAllById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return patientDao.getPatientById(id)
                .map(mapperAll)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")
                        )
                );
    }




    public Page<PatientDto> getAllPatient(Pageable pageable, BillState state, GetAllPatientRequest request)  {
        return patientDao.getAllPatientFilter(request, state, pageable).map(mapper::toDto);
    }

    public Page<PatientDto> getAllPatientArchive(Pageable pageable, GetAllPatientRequest request) {
        return patientDao.getAllPatientArchiveFilter(request, pageable).map(mapper::toDto);
    }


    public Page<PatientDto> getPatientByDoctorId(Pageable pageable, Long doctorId, GetAllPatientRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        doctorDao.findDoctorById(doctorId).
                orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        Page<Patient> patients = patientDao.getAllPatientByDoctorIdFilter(doctorId, request.getPatientName(),
                request.getMinAge(),
                request.getMaxAge(), request.getAdmissionDate(), pageable);


        return patients.map(mapper::toDto);
    }


    public Page<PatientDto> getPatientArchiveByDoctorId(Pageable pageable, Long doctorId, GetAllPatientRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        doctorDao.findDoctorById(doctorId).
                orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        Page<Patient> patients = patientDao.getAllPatientArchiveByDoctorIdFilter(doctorId, request.getPatientName(),
                request.getMinAge(),
                request.getMaxAge(), request.getAdmissionDate(), pageable);


        return patients.map(mapper::toDto);
    }


    public Page<PatientDto> getAllPatientByWard(Long wardId, String patientName, Long doctorId, Pageable pageable) {
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
//        wardDao.findWardById(wardId).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        messages.getString("resourceNotFound")
//                )
//        );
//
//        if (doctorId != null) {
//            return patientDao.getPatientByWardIdWithOnlySearchAndFilter(wardId, patientName, doctorId, pageable).map(mapper::toDto);
//        } else {
//            return patientDao.getPatientByWardIdWithOnlySearch(wardId, patientName, pageable).map(mapper::toDto);
//        }

        return patientDao.findAllPatientsByWardId(wardId, pageable).map(mapper::toDto);

    }

    public Page<PatientDto> getAllPatientArchiveByWard(Long wardId, String patientName, Long doctorId, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        wardDao.findWardById(wardId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (doctorId != null) {
            return patientDao.getPatientArchiveByWardIdWithOnlySearchAndFilter(wardId, patientName, doctorId, pageable).map(mapper::toDto);
        } else {
            return patientDao.getPatientArchiveByWardIdWithOnlySearch(wardId, patientName, pageable).map(mapper::toDto);
        }

    }

    public Page<PatientDto> getAllPatientWithFilter(String patientName, String doctorName, String wardName, Pageable pageable) {
        Page<Patient> patientsPage = patientDao.getAllPatientWithFilter(
                patientName, doctorName, wardName, pageable
        );

        return patientsPage.map(mapper::toDto);
    }


    private List<File> uploadMultiFile(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileService.saveFile(file);
                File newFile = new File(file.getContentType(), "Patient", url);
                fileList.add(newFile);
            }
        }
        return fileList;
    }


    private List<Image> uploadMultiImage(List<MultipartFile> images) throws IOException {
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String url = imageService.saveImage(image);
                Image newImage = new Image("ids", Patient.class.toString(), url);
                imageList.add(newImage);
            }
        }
        return imageList;
    }

    public Page<PatientDto> getAllArchivedPatients(Pageable pageable) {
        return patientDao.getAllArchivedPatient(pageable).map(mapper::toDto);
    }


    public void reEntryPatient(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                ));

        if (!patient.getIsArchived()) {
            throw new BadRequestException(
                    messages.getString("patientNotArchived")
            );

        } else {
            patient.setIsArchived(false);
            patient.setBed(null);
            patient.setBillState(BillState.UNPAID);
            Long entry = patient.getEntryNo();
            patient.setEntryNo(entry + 1);
            EntryTable entryTable = new EntryTable();
            entryTable.setTimesOfEntry(entry + 1);
            entryTable.setPatient(patient);
            entryTableDao.createEntryTable(entryTable);
            patientDao.updatePatient(patient);
        }

    }

    public Page<PatientDto> getAllPatientWithFilter(GetAllPatientRequest request, BillState state, Pageable pageable) {
        return patientDao.getAllPatientWithFilter(request, state, pageable).map(mapper::toDto);
    }
}
