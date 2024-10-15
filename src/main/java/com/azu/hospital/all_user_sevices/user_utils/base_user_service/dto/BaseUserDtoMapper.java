package com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BaseUserDtoMapper implements Function<BaseUser, BaseUserDto> {

    @Override
    public BaseUserDto apply(BaseUser baseUser) {
        BaseUserDto dto = new BaseUserDto();
        Long depId = baseUser.getDepartmentManger() == null ? null : baseUser.getDepartmentManger().getDepId();
        Long depAssId = baseUser.getDepartmentMangerAssistance() == null ? null : baseUser.getDepartmentMangerAssistance().getDepId() ;
        dto.setId(baseUser.getId());
        dto.setUsername(baseUser.getUsernameSpecial());
        dto.setEmail(baseUser.getEmail());
        dto.setRoles(baseUser.getRoles());
        dto.setEnabled(baseUser.getEnabled());
        dto.setAccountNonExpired(baseUser.getAccountNonExpired());
        dto.setCredentialsNonExpired(baseUser.getCredentialsNonExpired());
        dto.setAccountNonLocked(baseUser.getAccountNonLocked());
        dto.setDepMangerId(depId);
        dto.setDeepAssistantId(depAssId);



        if (baseUser instanceof Doctor doctor) {
            dto.setMartialStatus(((Doctor) baseUser).getMartialStatus());
            dto.setImage(((Doctor) baseUser).getImage());
            dto.setMobile(((Doctor) baseUser).getMobile());
            dto.setAddress(((Doctor) baseUser).getAddress());
            dto.setGender(((Doctor) baseUser).getGender());
            dto.setAge(((Doctor) baseUser).getAge());
            dto.setBirthday(((Doctor) baseUser).getBirthday());
            dto.setEmployeeDate(((Doctor) baseUser).getEmployeeDate());
            dto.setBloodGroup(((Doctor) baseUser).getBloodGroup());

            dto.setSpecialist(doctor.getSpecialist());
            dto.setSubSpecialist(doctor.getSubSpecialist());
            dto.setFrontPersonalId(((Doctor) baseUser).getFrontPersonalId());
            dto.setBackPersonalId(((Doctor) baseUser).getBackPersonalId());
            dto.setFrontMedicalId(((Doctor) baseUser).getFrontMedicalId());
            dto.setBackMedicalId(((Doctor) baseUser).getBackMedicalId());
            if (doctor.getWard() != null) {
                dto.setWardId(doctor.getWard().getWardId());
                dto.setWardName(doctor.getWard().getName());
            }
        }

        if (baseUser instanceof User user){
            dto.setMartialStatus(((User) baseUser).getMartialStatus());
            dto.setImage(((User) baseUser).getImage());
            dto.setMobile(((User) baseUser).getMobile());
            dto.setAddress(((User) baseUser).getAddress());
            dto.setGender(((User) baseUser).getGender());
            dto.setAge(((User) baseUser).getAge());
            dto.setBirthday(((User) baseUser).getBirthday());
            dto.setEmployeeDate(((User) baseUser).getEmployeeDate());
            dto.setBloodGroup(((User) baseUser).getBloodGroup());

            dto.setSpecialist(user.getSpecialist());
            dto.setFrontPersonalId(((User) baseUser).getFrontPersonalId());
            dto.setBackPersonalId(((User) baseUser).getBackPersonalId());
            dto.setFrontMedicalId(((User) baseUser).getFrontMedicalId());
            dto.setBackMedicalId(((User) baseUser).getBackMedicalId());

        }

        if (baseUser instanceof Permanent permanent){
            dto.setMartialStatus(((Permanent) baseUser).getMartialStatus());
            dto.setImage(((Permanent) baseUser).getImage());
            dto.setMobile(((Permanent) baseUser).getMobile());
            dto.setAddress(((Permanent) baseUser).getAddress());
            dto.setGender(((Permanent) baseUser).getGender());
            dto.setAge(((Permanent) baseUser).getAge());
            dto.setBirthday(((Permanent) baseUser).getBirthday());
            dto.setEmployeeDate(((Permanent) baseUser).getEmployeeDate());
            dto.setBloodGroup(((Permanent) baseUser).getBloodGroup());

            dto.setSpecialist(permanent.getSpecialist());
            dto.setFrontPersonalId(((Permanent) baseUser).getFrontPersonalId());
            dto.setBackPersonalId(((Permanent) baseUser).getBackPersonalId());
            dto.setFrontMedicalId(((Permanent) baseUser).getFrontMedicalId());
            dto.setBackMedicalId(((Permanent) baseUser).getBackMedicalId());
        }

        if (baseUser instanceof Nurse nurse){
            dto.setMartialStatus(((Nurse) baseUser).getMartialStatus());
            dto.setImage(((Nurse) baseUser).getImage());
            dto.setMobile(((Nurse) baseUser).getMobile());
            dto.setAddress(((Nurse) baseUser).getAddress());
            dto.setGender(((Nurse) baseUser).getGender());
            dto.setAge(((Nurse) baseUser).getAge());
            dto.setBirthday(((Nurse) baseUser).getBirthday());
            dto.setEmployeeDate(((Nurse) baseUser).getEmployeeDate());
            dto.setBloodGroup(((Nurse) baseUser).getBloodGroup());

            dto.setSpecialist(nurse.getSpecialist());
            dto.setFrontPersonalId(((Nurse) baseUser).getFrontPersonalId());
            dto.setBackPersonalId(((Nurse) baseUser).getBackPersonalId());
            dto.setFrontMedicalId(((Nurse) baseUser).getFrontMedicalId());
            dto.setBackMedicalId(((Nurse) baseUser).getBackMedicalId());
            if (nurse.getWard() != null && nurse.getWard().getWardId() != null) {
                dto.setWardId( nurse.getWard().getWardId());
                dto.setWardName(nurse.getWard().getName());
            }
        }
        return dto;
    }
}
