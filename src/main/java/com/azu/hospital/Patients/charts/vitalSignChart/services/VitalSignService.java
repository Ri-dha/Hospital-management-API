package com.azu.hospital.Patients.charts.vitalSignChart.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;
import com.azu.hospital.Patients.charts.vitalSignChart.dao.VitalSignDao;
import com.azu.hospital.Patients.charts.vitalSignChart.dto.VitalSignDto;
import com.azu.hospital.Patients.charts.vitalSignChart.dto.VitalSignDtoMapper;
import com.azu.hospital.Patients.charts.vitalSignChart.request.VitalSignRequest;
import com.azu.hospital.Patients.charts.vitalSignChart.utils.VitalSignRequestToEntity;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class VitalSignService {
    private final VitalSignDao vitalSignDao;
    private final PatientDao patientDao;
    private final VitalSignDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public VitalSignService(
            @Qualifier("VitalSignJpaDataAccess")
            VitalSignDao vitalSignDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            VitalSignDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.vitalSignDao = vitalSignDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public VitalSignDto getVitalSignById(Long vitalSignId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        VitalSign vitalSign = vitalSignDao.findById(vitalSignId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("VitalSignChartNotFound") + " " + vitalSignId
                        )
                );

        return dtoMapper.toDto(vitalSign);
    }

    public List<VitalSignDto> getAllVitalSignByPatientId(Long patientId) {
        List<VitalSignDto> response = vitalSignDao.getAllVitalSignByPatientId(patientId)
                .stream()
                .map(dtoMapper::toDto)
                .toList();

        return response;
    }

    public DtoForReturnIdOnly createNewVitalSign(Long patientId, VitalSignRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        VitalSign vitalSign = VitalSignRequestToEntity.toEntity(request);

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("patientHasNoMedicalHistory")
            );

        vitalSign.setPatient(patient);

        VitalSign saved = vitalSignDao.save(vitalSign);

        return dtoMapper.toResponseDto(saved);
    }

    public DtoForReturnIdOnly updateExistsVitalSign(Long vitalSignId, VitalSignRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        VitalSign newVitalSign = VitalSignRequestToEntity.toEntity(request);

        VitalSign existVitalSign = vitalSignDao.findById(vitalSignId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("VitalSignChartNotFound") + " " + vitalSignId
                        )
                );


        if (newVitalSign.getPainLevel() != null) {
            newVitalSign.setPainLevel(request.painLevel());
        }
        if (newVitalSign.getSpo() != null) {
            newVitalSign.setSpo(request.spo());
        }
        if (newVitalSign.getBloodSugar() != null) {
            newVitalSign.setBloodSugar(request.bloodSugar());
        }
        if (newVitalSign.getRespiratoryRate() != null) {
            newVitalSign.setRespiratoryRate(request.respiratoryRate());
        }
        if (newVitalSign.getSystolicBloodPressure() != null) {
            newVitalSign.setSystolicBloodPressure(request.systolicBloodPressure());
        }
        if (newVitalSign.getDiastolicBloodPressure() != null) {
            newVitalSign.setDiastolicBloodPressure(request.diastolicBloodPressure());
        }
        if (newVitalSign.getMainBloodPressure() != null) {
            newVitalSign.setMainBloodPressure(request.mainBloodPressure());
        }
        if (newVitalSign.getCoreTemperature() != null) {
            newVitalSign.setCoreTemperature(request.coreTemperature());
        }
        if (newVitalSign.getSkinTemperature() != null) {
            newVitalSign.setSkinTemperature(request.skinTemperature());
        }
        if (newVitalSign.getPulseRate() != null) {
            newVitalSign.setPulseRate(request.pulseRate());
        }


//        if (
//                newVitalSign.getPainLevel().equals(existVitalSign.getPainLevel())
//                        && newVitalSign.getSpo().equals(existVitalSign.getSpo())
//                        && newVitalSign.getRespiratoryRate().equals(existVitalSign.getRespiratoryRate())
//                        && newVitalSign.getBloodSugar().equals(existVitalSign.getBloodSugar())
//                        && newVitalSign.getSystolicBloodPressure().equals(existVitalSign.getSystolicBloodPressure())
//                        && newVitalSign.getDiastolicBloodPressure().equals(existVitalSign.getDiastolicBloodPressure())
//                        && newVitalSign.getMainBloodPressure().equals(existVitalSign.getMainBloodPressure())
//                        && newVitalSign.getCoreTemperature().equals(existVitalSign.getCoreTemperature())
//                        && newVitalSign.getSkinTemperature().equals(existVitalSign.getSkinTemperature())
//                        && newVitalSign.getPulseRate().equals(existVitalSign.getPulseRate())
//        ) {
//            throw new BadRequestException(
//                    messages.getString("noChanges")
//
//            );
//        }

        newVitalSign.setPatient(existVitalSign.getPatient());

        VitalSign update = vitalSignDao.save(newVitalSign);

        return dtoMapper.toResponseDto(update);
    }
}
