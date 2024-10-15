package com.azu.hospital.Patients.Patient.services;

import com.azu.hospital.Patients.MedicalHistory.dao.MedicalHistoryDao;
import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.Patient.dto.PatientDtoMapper;
import com.azu.hospital.Patients.Patient.request.CreatePatientRequest;
import com.azu.hospital.Patients.entryTable.dao.EntryTableDao;
import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.accounting.insurance.dao.InsuranceDao;
import com.azu.hospital.accounting.insurance.entity.Insurance;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentDao;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.AnesthesiaType;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.operation.OperationTriage;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.Image;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientAddService {
    private final PatientDao patientDao;
    private final DoctorDao doctorDao;
    private final EntryTableDao entryTableDao;

    private final PatientDtoMapper mapper;
    private final PermanentDao permanentDao;
    private final FileService fileService;

    private final ImageService imageService;
    private final InsuranceDao insuranceDao;
    private final IPatientWalletService patientWalletService;

    private final MedicalHistoryDao medicalHistoryDao;

    private final OperationDao operationDao;

    private final MainSurgicalListDao mainSurgicalListDao;
    private final ExceptionsMessageReturn messageReturn;

    private final WardDao wardDao;
    private final BedDao bedDao;



    @Autowired
    public PatientAddService(@Qualifier("patientRepo") PatientDao patientDao, @Qualifier("DoctorJpa") DoctorDao doctorDao,
                             @Qualifier("EntryTableJpa") EntryTableDao entryTableDao, @Qualifier("patientDtoMapper") PatientDtoMapper mapper,
                             @Qualifier("PermanentJpa") PermanentDao permanentDao, @Qualifier("fileService") FileService fileService,
                             @Qualifier("imageService") ImageService imageService, @Qualifier("insuranceJpa") InsuranceDao insuranceDao,
                             @Qualifier("PatientWalletServiceImp") IPatientWalletService patientWalletService, @Qualifier("MedicalHistoryRepo") MedicalHistoryDao medicalHistoryDao,
                             @Qualifier("OperationJpa") OperationDao operationDao, MainSurgicalListDao mainSurgicalListDao, @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn, WardDao wardDao, BedDao bedDao) {
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
        this.entryTableDao = entryTableDao;
        this.mapper = mapper;
        this.permanentDao = permanentDao;
        this.fileService = fileService;
        this.imageService = imageService;
        this.insuranceDao = insuranceDao;
        this.patientWalletService = patientWalletService;
        this.medicalHistoryDao = medicalHistoryDao;
        this.operationDao = operationDao;
        this.mainSurgicalListDao = mainSurgicalListDao;
        this.messageReturn = messageReturn;
        this.wardDao = wardDao;
        this.bedDao = bedDao;
    }

    @Transactional
    public PatientDto createNewPatient(
            CreatePatientRequest request
    ) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(request.getPatientDate().getBirthDate(), formatter);
        LocalDate currentDate = LocalDate.now();
        request.getPatientDate().setAge(Period.between(birthdate, currentDate).getYears());

        Patient patient = new Patient.Builder().
                withJobInfo(request.getJobInfo()).
                withPatientAddress(request.getPatientAddress()).
                withPatientData(request.getPatientData()).
                withPatientDate(request.getPatientDate()).
                withPatientContact(request.getPatientContact()).
                withPatientMedicalInfo(request.getPatientMedicalInfo()).
                build();

        Doctor doctor = getDoctor(request, messages);

        extractedPermanent(request, messages, patient);


        extractedImageAndFile(request, patient);

        thisMethodForPoliceStatus(request, patient);
        thisMethodForPatientNameValidations(messages, patient);

        Patient newPatient = patientDao.insertPatient(patient);

        PatientDoctor patientDoctor = new PatientDoctor(PatientDoctorState.Main);
        patientDoctor.setPatient(patient);
        patientDoctor.setDoctor(doctor);
        newPatient.getDoctor().add(patientDoctor);

        theseTwoMethodForAccounting(newPatient);

        EntryTable entryTable = new EntryTable();
        entryTable.setPatient(newPatient);
        entryTable.setTimesOfEntry(1L);
        entryTableDao.createEntryTable(entryTable);

        thisMethodForCheckingIfSetOperationOrNot(request, messages, newPatient);

        return mapper.toDto(newPatient);

    }

    private void thisMethodForCheckingIfSetOperationOrNot(CreatePatientRequest request, ResourceBundle messages, Patient newPatient) {

        if (request.getOperationId() != null) {
               medicalHistoryCreation(request, newPatient, messages);
           }else {
            onlyMedicalHistoryCreation(request, newPatient, messages);
       }
    }

    private void theseTwoMethodForAccounting(Patient newPatient) {
        patientWalletService.createNewPatientWallet(newPatient);
        Insurance insurance = new Insurance();
        insurance.setPatient(newPatient);
        insurance.setDepositAmount(BigDecimal.ZERO);
        insurance.setInsuranceAmount(BigDecimal.ZERO);
        insuranceDao.createInsurance(insurance);
    }

    private void thisMethodForPatientNameValidations(ResourceBundle messages, Patient patient) {
        if (patientDao.existsByFullName(patient.getPatientData().getFullName())) {
            throw new BadRequestException(
                    messages.getString("fullNameExist")
            );
        }
    }

    private void thisMethodForPoliceStatus(CreatePatientRequest request, Patient patient) throws IOException {
        if (request.getPoliceStatus() != null && !request.getPoliceStatus().isEmpty()) {
            for (MultipartFile images : request.getPoliceStatus()) {
                if (!images.isEmpty()) {
                    String url = imageService.saveImage(images);
                    Image image = new Image("policeStatus", Patient.class.toString(), url);
                    patient.getImages().add(image);
                }
            }
        }
    }

    private void extractedImageAndFile(CreatePatientRequest request, Patient patient) throws IOException {
        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<File> fileList = uploadMultiFile(request.getFiles());
            patient.setFiles(fileList);
        }

        if (request.getTests() != null && !request.getTests().isEmpty()) {
            List<File> fileList = uploadMultiFile(request.getTests());
            patient.setTests(fileList);
        }


        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String profileImage = fileService.saveFile(request.getImage());
            patient.setImage(profileImage);
        }


        if (request.getIds() != null && !request.getIds().isEmpty()) {
            List<Image> ids = uploadMultiImage(request.getIds());
            patient.setImages(ids);

        }
    }

    private void extractedPermanent(CreatePatientRequest request, ResourceBundle messages, Patient patient) {
        if (request.getPermanentDoctor() != null) {
            Permanent permanentDoctor = permanentDao.findPermanentById(request.getPermanentDoctor()).
                    orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("permanentNotFound") + request.getPermanentDoctor()
                            )
                    );
            patient.setPermanentDoctor(permanentDoctor);
        }
    }

    private Doctor getDoctor(CreatePatientRequest request, ResourceBundle messages) {
        return doctorDao.findDoctorById(request.getDoctorId()).
                        orElseThrow(
                                () -> new ResourceNotFoundException(
                                        messages.getString("doctorNotFound") + request.getDoctorId()
                                )
                        );
    }

    private void medicalHistoryCreation(CreatePatientRequest request, Patient newPatient, ResourceBundle messages) {
        if (
                request.getWardId() != null && request.getBedId() != null
                        && request.getOperationId() != null
        ) {

            thisMethodForAddMedicalHistory(newPatient);
            thisMethodForSetOperation(request, newPatient, messages);
            thisMethodForWard(request, newPatient, messages);
            thisMethodForBed(request, newPatient, messages);


        }
    }

    private void thisMethodForSetOperation(CreatePatientRequest request, Patient newPatient, ResourceBundle messages) {
        Operation operation = new Operation(
                "",
                OperationStates.Waiting,
                AnesthesiaType.GENERAL,
                Instant.now(),
                OperationTriage.ElectiveSurgery
        );
        operation.setPatient(newPatient);
        MainSurgicalList surgicalList =mainSurgicalListDao.getSurgicalById(request.getOperationId()).
                orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("surgicalListWithThisNameNotFound") + " " + request.getOperationId()
                        )
                );
        operation.setSurgical(surgicalList);
        operationDao.createNewOperation(operation);
        newPatient.getPatientData().setOperation(operation.getSurgical().getSurgicalName());
    }

    private void thisMethodForBed(CreatePatientRequest request, Patient newPatient, ResourceBundle messages) {
        Bed bed = bedDao.getBedByIdAndWardIdAndBedNotReserved(request.getBedId()).
                orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("bedNotFound") + request.getBedId()
                        )
                );

        newPatient.setBed(bed);
        bed.setPatient(newPatient);
    }

    private void onlyMedicalHistoryCreation(CreatePatientRequest request, Patient newPatient, ResourceBundle messages) {
        if (
                request.getWardId() != null && request.getBedId() != null
        ) {

            thisMethodForAddMedicalHistory(newPatient);

            thisMethodForWard(request, newPatient, messages);
            thisMethodForBed(request, newPatient, messages);


        }
    }

    private void thisMethodForAddMedicalHistory(Patient newPatient) {
        MedicalHistory medicalHistory = new MedicalHistory(
                "",
                "",
                "",
                "",
                "",
                "",
                PatientTriageEnum.NonUrgent
        );

        medicalHistory.setPatient(newPatient);
        newPatient.setMedicalHistory(List.of(medicalHistory));
        medicalHistoryDao.insertMedicalHistory(medicalHistory);
    }

    private void thisMethodForWard(CreatePatientRequest request, Patient newPatient, ResourceBundle messages) {
        newPatient.setWard(wardDao.findWardById(request.getWardId()).
                orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("wardNotFound") + request.getWardId()
                        )
                ));
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

}
