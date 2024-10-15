package com.azu.hospital.all_user_sevices.user_requests.userraequestparts;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.specialist_service.HospitalSpecialty;

import com.azu.hospital.utils.enums.EnumRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;


@Data
@NoArgsConstructor
public class UserPersonalJopInfo {


    @NotBlank(message = "Blood Group Required")
    @NotNull(message = "Blood Group Required")
    @NotEmpty(message = "Blood Group Required")
    private String bloodGroup;




    @NotNull(message = "User Role Required")
    @Enumerated(EnumType.STRING)
    private Set<String> roles;

    private String specialist;

    private String subSpecialty;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;


    public UserPersonalJopInfo(String bloodGroup,

                               Set<String> roles,
                               String specialist,
                               String subSpecialty,
                               Boolean enabled,
                               Boolean accountNonExpired,
                               Boolean credentialsNonExpired,
                               Boolean accountNonLocked) {
        this.bloodGroup = bloodGroup;

        this.roles = roles;
        this.specialist = specialist;
        this.subSpecialty = subSpecialty;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    public UserPersonalJopInfo(String bloodGroup,
                               Set<String> roles,
                               String specialist,
                               String subSpecialty) {
        this.bloodGroup = bloodGroup;

        this.roles = roles;
        this.specialist = specialist;
        this.subSpecialty = subSpecialty;
    }

    public UserPersonalJopInfo(
            String bloodGroup,
            Set<String> roles, String specialist
    ) {
        this.bloodGroup = bloodGroup;

        this.roles = roles;
        this.specialist = specialist;
    }

    public UserPersonalJopInfo(String bloodGroup,

                               Set<String> roles,
                               String specialist,
                               Boolean enabled,
                               Boolean accountNonExpired,
                               Boolean credentialsNonExpired,
                               Boolean accountNonLocked) {
        this.bloodGroup = bloodGroup;

        this.roles = roles;
        this.specialist = specialist;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }



    public UserPersonalJopInfo(String bloodGroup,

                               String specialist,
                               String subSpecialty,
                               Boolean enabled,
                               Boolean accountNonExpired,
                               Boolean credentialsNonExpired,
                               Boolean accountNonLocked) {

        this.bloodGroup = bloodGroup;

        this.specialist = specialist;
        this.subSpecialty = subSpecialty;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;

    }
}
