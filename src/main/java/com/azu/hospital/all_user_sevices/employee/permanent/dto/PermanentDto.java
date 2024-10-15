package com.azu.hospital.all_user_sevices.employee.permanent.dto;



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
@JsonSerialize(using = CustomPermanentDtoSerializer.class)
public class PermanentDto {

    private Long id;
    private UserPersonalInfo permanentPersonalInfo;
    private String image;
    private UserPersonalContact permanentPersonalContact;
    private UserPersonalDate permanentPersonalDate;
    private UserPersonalJopInfo permanentPersonalJopInfo;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private String frontPersonalId;
    private String backPersonalId;
    private String frontMedicalId;
    private String backMedicalId;
    private Set<String> roles;



    public PermanentDto() {
    }

    @JsonCreator
    public PermanentDto(
            @JsonProperty("PermanentId") Long id,
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

            @NotNull(message = "Permanent Role Required")
            @JsonProperty("roles") Set<String> roles,
            @JsonProperty("specialty") String specialty,
            @JsonProperty("enabled") Boolean enabled,
            @JsonProperty("accountNonExpired") Boolean accountNonExpired,
            @JsonProperty("credentialsNonExpired") Boolean credentialsNonExpired,
            @JsonProperty("accountNonLocked") Boolean accountNonLocked,
            @JsonProperty("frontPersonalId") String frontPersonalId,
            @JsonProperty("backPersonalId") String backPersonalId,
            @JsonProperty("frontMedicalId") String frontMedicalId,
            @JsonProperty("backMedicalId") String backMedicalId
    ) {
        this.id = id;
        this.permanentPersonalInfo = new UserPersonalInfo(username, gender, martialStatus);
        this.image = image;
        this.permanentPersonalContact = new UserPersonalContact(email, mobile, address);
        this.permanentPersonalDate = new UserPersonalDate(birthday, employeeDate);
        this.permanentPersonalJopInfo = new UserPersonalJopInfo(bloodGroup,roles, specialty);
        this.enabled = enabled;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.frontPersonalId = frontPersonalId;
        this.backPersonalId = backPersonalId;
        this.frontMedicalId = frontMedicalId;
        this.backMedicalId = backMedicalId;
    }



}

