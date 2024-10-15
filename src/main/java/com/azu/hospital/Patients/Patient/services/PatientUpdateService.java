package com.azu.hospital.Patients.Patient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.request.UpdatePatientRequest2;
import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.Image;
import com.azu.hospital.utils.image.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientUpdateService {
    private final PatientDao patientDao;

    private final DoctorDao doctorDao;

    private final ImageService imageService;

    private final FileService fileService;

    private final ExceptionsMessageReturn messageReturn;

    public PatientUpdateService(PatientDao patientDao, DoctorDao doctorDao, ImageService imageService, FileService fileService, ExceptionsMessageReturn messageReturn) {
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
        this.imageService = imageService;
        this.fileService = fileService;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void updatePatient(UpdatePatientRequest2 request, HttpServletRequest httpRequest) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        // Extract the username from JWT
        // jwtService.extractUsername(httpRequest.getHeader("Authorization").replace("Bearer ", ""));

        // Create a DateTimeFormatter and calculate the age

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate birthdate = LocalDate.parse(request.getPatientDate().getBirthDate(), formatter);
//        LocalDate currentDate = LocalDate.now();
//        request.getPatientDate().setAge(Period.between(birthdate, currentDate).getYears());

        Patient existingPatient = patientDao.getPatientById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        boolean changes = false;
        if (request.getDoctorId() != null){
            Doctor doctor = doctorDao.findDoctorById(request.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messages.getString("doctorNotFound") + request.getDoctorId()
                    ));
            existingPatient.getDoctor().clear();
            PatientDoctor patientDoctor = new PatientDoctor(PatientDoctorState.Main);
            patientDoctor.setPatient(existingPatient);
            patientDoctor.setDoctor(doctor);
            existingPatient.getDoctor().add(patientDoctor);
            changes = true;
        }
        if(request.getIds() != null && !request.getIds().isEmpty()){
            List<Image> ids = uploadMultiImage(request.getIds());
            existingPatient.getImages().addAll(ids);
            changes = true;
        }

        if(request.getPoliceStatus() != null && !request.getPoliceStatus().isEmpty()){
            for (MultipartFile images : request.getPoliceStatus()) {
                if (!images.isEmpty()) {
                    String url = imageService.saveImage(images);
                    Image image = new Image("policeStatus", Patient.class.toString(), url);
                    existingPatient.getImages().add(image);
                }
            }
            changes = true;
        }

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<File> fileList = uploadMultiFile(request.getFiles());
            existingPatient.setFiles(fileList);
        }
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String profileImage = fileService.saveFile(request.getImage());
            existingPatient.setImage(profileImage);
            changes = true;
        }

        if (request.getTests() != null && !request.getTests().isEmpty()) {
            List<File> fileList = uploadMultiFile(request.getTests());
            existingPatient.getTests().addAll(fileList);
            changes = true;
        }
        if(request.getJob() != null){
            existingPatient.getJobInfo().setJob(request.getJob());
            changes = true;
        }
        if(request.getJobType() != null){
            existingPatient.getJobInfo().setJobType(request.getJobType());
            changes = true;
        }
        if(request.getBirthAddress() != null){
            existingPatient.getPatientAddress().setBirthAddress(request.getBirthAddress());
            changes = true;
        }
        if(request.getAddress() != null){
            existingPatient.getPatientAddress().setAddress(request.getAddress());
            changes = true;
        }
        if(request.getEmail() != null){
            existingPatient.getPatientContact().setEmail(request.getEmail());
            changes = true;
        }
        if(request.getMobile() != null){
            existingPatient.getPatientContact().setMobile(request.getMobile());
            changes = true;
        }
        if(request.getRelativeMobile() != null){
            existingPatient.getPatientContact().setRelativeMobile(request.getRelativeMobile());
            changes = true;
        }
        if(request.getFullName() != null){
            existingPatient.getPatientData().setFullName(request.getFullName());
            changes = true;
        }
        if(request.getMotherName() != null){
            existingPatient.getPatientData().setMotherName(request.getMotherName());
            changes = true;
        }
        if(request.getGender() != null){
            existingPatient.getPatientData().setGender(request.getGender());
            changes = true;
        }
        if(request.getWeight() != null){
            existingPatient.getPatientData().setWeight(request.getWeight());
            changes = true;
        }
        if(request.getHeight() != null){
            existingPatient.getPatientData().setHeight(request.getHeight());
            changes = true;
        }
        if(request.getCertification() != null){
            existingPatient.getPatientData().setCertification(request.getCertification());
            changes = true;
        }
        if(request.getLiveEnum() != null){
            existingPatient.getPatientData().setLiveEnum(request.getLiveEnum());
            changes = true;
        }
        if(request.getSocialState() != null){
            existingPatient.getPatientData().setSocialState(request.getSocialState());
            changes = true;
        }
        if(request.getOperation() != null){
            existingPatient.getPatientData().setOperation(request.getOperation());
            changes = true;
        }
        if(request.getAdmissionDate() != null){
            existingPatient.getPatientDate().setAdmissionDate(request.getAdmissionDate());
            changes = true;
        }
        if(request.getBirthDate() != null){
            existingPatient.getPatientDate().setBirthDate(request.getBirthDate());
            changes = true;
        }
//
//        if(request.getAge() != null){
//            existingPatient.getPatientDate().setAge(request.getAge());
//            changes = true;
//        }
        if(request.getApparentImpairments() != null){
            existingPatient.getPatientMedicalInfo().setApparentImpairments(request.getApparentImpairments());
            changes = true;
        }
        if(request.getTimeOfEntries() != null){
            existingPatient.getPatientMedicalInfo().setTimeOfEntries(request.getTimeOfEntries());
            changes = true;
        }
        if(request.getReferredFrom() != null){
            existingPatient.getPatientMedicalInfo().setReferredFrom(request.getReferredFrom());
            changes = true;
        }
        if(request.getTransformations() != null){
            existingPatient.getPatientMedicalInfo().setTransformations(request.getTransformations());
            changes = true;
        }


        // Handle image update
//        if (request.getImage() != null && !request.getImage().isEmpty()) {
//            String profileImage = fileService.saveImage(request.getImage());
//            existingPatient.setImage(profileImage);
//            changed = true;
//        }

        // Handle other files/images update for other fields in a similar way
//        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
//            List<File> otherFiles = new ArrayList<>();
//            for (MultipartFile file : request.getFiles()) {
//                if (!file.isEmpty()) {
//                    String url = fileService.saveFile(file);
//                    File otherFile = new File(file.getContentType(), "Patient", url);
//                    existingPatient.getFiles().addAll(otherFiles);
//                }
//            }
//            existingPatient.getFiles().add((File) otherFiles);
//            changes = true;
//        }

        // Save the updated patient
        if (!changes) {
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }

        patientDao.updatePatient(existingPatient);

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
