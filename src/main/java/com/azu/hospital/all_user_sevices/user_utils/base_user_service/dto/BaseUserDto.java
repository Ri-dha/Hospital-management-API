package com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
@Data
public class BaseUserDto {

    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private Gender gender;
    private String birthday;
    private Integer age;
    private String mobile;
    private EnumMartialStatus martialStatus;
    private String address;
    private String bloodGroup;
    private Long monthlySalary;
    private String employeeDate;
    private String image;
    private String frontPersonalId;
    private String backPersonalId;
    private String frontMedicalId;
    private String backMedicalId;
//    private Integer appointmentNumber;
    private String specialist;
    private String subSpecialist;
    private Long depMangerId;
    private Long deepAssistantId;
    private Long wardId;
    private String wardName;


    public BaseUserDto() {
    };


    public BaseUserDto(Long id, String username, String email, Set<Role> roles,
                       Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired,
                       Boolean accountNonLocked, Gender gender, String birthday, Integer age, String mobile,
                       EnumMartialStatus martialStatus, String address, String bloodGroup, Long monthlySalary,
                       String employeeDate, String image, String frontPersonalId, String backPersonalId,
                       String frontMedicalId, String backMedicalId,
//                       Integer appointmentNumber,
                       String specialist,
                       String subSpecialist,  Long depMangerId,   @JsonProperty("wardId") Long wardId,
                       @JsonProperty("wardName") String wardName,
     Long deepAssistantId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.mobile = mobile;
        this.martialStatus = martialStatus;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.monthlySalary = monthlySalary;
        this.employeeDate = employeeDate;
        this.image = image;
        this.frontPersonalId = frontPersonalId;
        this.backPersonalId = backPersonalId;
        this.frontMedicalId = frontMedicalId;
        this.backMedicalId = backMedicalId;
//        this.appointmentNumber = appointmentNumber;
        this.specialist = specialist;
        this.subSpecialist = subSpecialist;
        this.depMangerId = depMangerId;
        this.deepAssistantId = deepAssistantId;
        this.wardId = wardId;
        this.wardName = wardName;
    }
}

