package com.azu.hospital.consultant.consultantPatient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.dto.ConsultantPatientDto;
import com.azu.hospital.consultant.consultantPatient.dto.ConsultantPatientDtoMapper;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.consultant.consultantPatient.request.CreateNewPatientRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.Image;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ConsultantPatientService {

    private final ConsultantPatientDao consultantPatientDao;
    private final DoctorDao doctorDao;
    private final ConsultantPatientDtoMapper mapper;
    private final FileService fileService;
    private final ImageService imageService;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ConsultantPatientService(
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            ConsultantPatientDtoMapper mapper,
            FileService fileService,
            ImageService imageService, ExceptionsMessageReturn messageReturn
    ) {
        this.consultantPatientDao = consultantPatientDao;
        this.doctorDao = doctorDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.imageService = imageService;
        this.messageReturn = messageReturn;
    }


    public ConsultantPatientDto createNewPatient(CreateNewPatientRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(1L).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        ); // TODO: 10/25/2023 Replace id with auth user

        ConsultantPatient consultantPatient = ConsultantPatient.builder()
                .consultantPatientInfo(request.getConsultantPatientInfo())
                .consultantPatientAddress(request.getConsultantPatientAddress())
                .consultantPatientJobInfo(request.getConsultantPatientJobInfo())
                .note(request.getNote())
                .doctor(doctor)
                .build();


        if (request.getFiles() != null && !request.getFiles().isEmpty()){
            List<File> fileList = new ArrayList<>();
            for(MultipartFile files: request.getFiles()){
                if (!files.isEmpty()){
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType() , "Patient" ,url );
                    fileList.add(file);
                }
            }
            consultantPatient.setFiles(fileList);
        }


        if (request.getIds() != null && !request.getIds().isEmpty()){
            for(MultipartFile images: request.getIds()){
                if (!images.isEmpty()) {
                    String url = imageService.saveImage(images);
                    Image image = new Image("ids" , Patient.class.toString() ,url );
                    consultantPatient.getImages().add(image);
                }
            }
        }

        consultantPatient = consultantPatientDao.createNewConsultantPatient(consultantPatient);

        return mapper.toDto(consultantPatient);
    }

    public List<ConsultantPatientDto> getAllByDoctorId(Long doctorId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(1L).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );// TODO: 10/25/2023 Replace id with auth user

        return consultantPatientDao.getAllDoctorId(doctorId).stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public ConsultantPatientDto getByPatientId(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return mapper.toDto(consultantPatient);

    }


}
