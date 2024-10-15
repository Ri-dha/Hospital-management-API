package com.azu.hospital.all_user_sevices.employee.nurses.dto;


import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_requests.userraequestparts.UserPersonalContact;
import com.azu.hospital.all_user_sevices.user_requests.userraequestparts.UserPersonalDate;
import com.azu.hospital.all_user_sevices.user_requests.userraequestparts.UserPersonalInfo;
import com.azu.hospital.all_user_sevices.user_requests.userraequestparts.UserPersonalJopInfo;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.Gender;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@JsonSerialize(using = CustomNurseDtoSerializer.class)
public class NurseDto {

    private Long wardId;
    private String wardName;
    private Long id;
    private UserPersonalInfo nursePersonalInfo;
    private String image;
    private UserPersonalContact nursePersonalContact;
    private UserPersonalDate nursePersonalDate;
    private UserPersonalJopInfo nursePersonalJopInfo;
    private String frontPersonalId;
    private String backPersonalId;
    private String frontMedicalId;
    private String backMedicalId;
    private Set<String> roles;



    public NurseDto() {
    };

    @JsonCreator
    public NurseDto(
            @JsonProperty("NurseId") Long id,
            @JsonProperty("username") String username,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("martialStatus") EnumMartialStatus martialStatus,
            @JsonProperty("image") String image,
            @JsonProperty("email") String email,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("address") String address,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("employeeDate") String employeeDate,
            @JsonProperty("bloodGroup") String bloodGroup,
            @NotNull(message = "Nurse Role Required")
            @JsonProperty("roles") Set<String> roles,
            @JsonProperty("specialty") String specialty,
            @JsonProperty("enabled") Boolean enabled,
            @JsonProperty("accountNonExpired") Boolean accountNonExpired,
            @JsonProperty("credentialsNonExpired") Boolean credentialsNonExpired,
            @JsonProperty("accountNonLocked") Boolean accountNonLocked,
            @JsonProperty("frontPersonalId") String frontPersonalId,
            @JsonProperty("backPersonalId") String backPersonalId,
            @JsonProperty("frontMedicalId") String frontMedicalId,
            @JsonProperty("backMedicalId") String backMedicalId,
            @JsonProperty("wardId") Long wardId,
            @JsonProperty("wardName") String wardName
    ) {
        this.id = id;
        this.nursePersonalInfo = new UserPersonalInfo(username, gender, martialStatus);
        this.image = image;
        this.nursePersonalContact = new UserPersonalContact(email, mobile, address);
        this.nursePersonalDate = new UserPersonalDate(birthday, employeeDate);
        this.nursePersonalJopInfo = new UserPersonalJopInfo(bloodGroup, roles, specialty, enabled,  accountNonExpired,  credentialsNonExpired,  accountNonLocked);
        this.frontPersonalId = frontPersonalId;
        this.backPersonalId = backPersonalId;
        this.frontMedicalId = frontMedicalId;
        this.backMedicalId = backMedicalId;
        this.wardId = wardId;
        this.wardName = wardName;
    }



}

