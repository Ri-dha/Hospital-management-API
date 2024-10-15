package com.azu.hospital.Patients.surgicalList.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.surgicalList.dao.SurgicalListDao;
import com.azu.hospital.Patients.surgicalList.dto.SurgicalListDto;
import com.azu.hospital.Patients.surgicalList.dto.SurgicalListDtoMapper;
import com.azu.hospital.Patients.surgicalList.entity.SurgicalList;
import com.azu.hospital.Patients.surgicalList.request.AddDoctorToSurgicalRequest;
import com.azu.hospital.Patients.surgicalList.request.SurgicalListCreateRequest;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDtoMapper;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.patient.SurgicalStateEnum;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurgicalListService {

    private final PatientDao patientDao;
    private final DoctorDao doctorDao;
    private final DoctorDtoMapper doctorDtoMapper;
    private final SurgicalListDtoMapper mapper;
    private final SurgicalListDao surgicalListDao;

    private static final String APPOINTMENT_TIME_SUFFIX = "T10:30:00Z";


    @Autowired
    public SurgicalListService(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            @Qualifier("surgicalListJpa") SurgicalListDao surgicalListDao,
            DoctorDtoMapper doctorDtoMapper,
            SurgicalListDtoMapper mapper
    ) {
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
        this.doctorDtoMapper = doctorDtoMapper;
        this.surgicalListDao = surgicalListDao;
        this.mapper = mapper;
    }



    public void createSurgicalList(SurgicalListCreateRequest request) {

        Patient patient = patientDao.getPatientById(request.getPatientId())
                .orElseThrow(
                () -> new ResourceNotFoundException("Patient doesn't Exists")
        );


        Instant surgicalDate = Instant.parse(request.getDate() + APPOINTMENT_TIME_SUFFIX);


        SurgicalList surgicalList = new SurgicalList(
                request.getSurgicalName(),
                request.getAnesthesiaType(),
                surgicalDate
        );

        surgicalList.setPatient(patient);
        surgicalList.getDoctors().add(patient.getDoctor().get(0).getDoctor());

        surgicalListDao.createSurgicalList(surgicalList);

    }

    public Page<SurgicalListDto> getAllSurgicalList(String date, Pageable pageable){
        Instant surgicalDate = Instant.parse(date + APPOINTMENT_TIME_SUFFIX);
        return surgicalListDao.getAllSurgicalList(surgicalDate , pageable).map(mapper::toDto);
    }


    public List<DoctorDto> getDoctorSurgicalList(Long surgicalListId){
        SurgicalList surgicalList = surgicalListDao.findById(surgicalListId).orElseThrow(
                () -> new ResourceNotFoundException("Surgical Doesn't Exists")
        );
        return surgicalList.getDoctors().stream().map(doctorDtoMapper::apply).collect(Collectors.toList());
    }


    public void addDoctorToSurgical(AddDoctorToSurgicalRequest request) {

        SurgicalList surgicalList = surgicalListDao.findById(request.getSurgicalId()).orElseThrow(
                () -> new ResourceNotFoundException("Surgical Doesn't Exists")
        );

        if (surgicalList.getState() == SurgicalStateEnum.Done) {
            throw new BadRequestException("Can't add doctor for this surgical");
        }

        List<Doctor> doctors = doctorDao.getDoctorByIdList(request.getDoctorIds());

        if (doctors.size() != request.getDoctorIds().size()) {
            throw new ResourceNotFoundException("One or many doctor doesn't exists");
        }

        for (Doctor doctor:doctors){
            surgicalList.getDoctors().add(doctor);
        }

        surgicalListDao.updateSurgicalList(surgicalList);

    }


    public SurgicalListDto surgicalInSurgical(Long id , String state) {

        SurgicalList surgicalList = surgicalListDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Surgical Doesn't Exists")
        );

        surgicalList.setState(SurgicalStateEnum.valueOf(state));

        surgicalListDao.updateSurgicalList(surgicalList);

        return mapper.toDto(surgicalList);

    }


    public SurgicalListDto surgicalDone(Long id , String state) {

        SurgicalList surgicalList = surgicalListDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Surgical Doesn't Exists")
        );

        surgicalList.setState(SurgicalStateEnum.valueOf(state));

        surgicalListDao.updateSurgicalList(surgicalList);

        return mapper.toDto(surgicalList);

    }


}
