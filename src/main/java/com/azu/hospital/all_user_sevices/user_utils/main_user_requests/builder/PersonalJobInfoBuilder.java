package com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.PersonalJobInfo;
import com.azu.hospital.utils.enums.EnumRole;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public class PersonalJobInfoBuilder {

    private String bloodGroup;
    private Set<EnumRole> roles;
    @NotNull(message = "specialist required")
    private String specialist;
    private String subSpecialty;

    public PersonalJobInfoBuilder() {
    };

    public PersonalJobInfoBuilder withBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }


    public PersonalJobInfoBuilder withRoles(Set<EnumRole> roles) {
        this.roles = roles;
        return this;
    }

    public PersonalJobInfoBuilder withSpecialty(String specialist) {
        this.specialist = specialist;
        return this;
    }

    public PersonalJobInfoBuilder withSubSpecialty(String subSpecialty) {
        this.subSpecialty = subSpecialty;
        return this;
    }

    public PersonalJobInfo build() {
        final String finalBloodGroup = this.bloodGroup;
        final Set<EnumRole> finalRoles = this.roles;
        final String finalSpecialty = this.specialist;
        final String finalSubSpecialty = this.subSpecialty;

        return new PersonalJobInfo() {
            @Override
            public String getBloodGroup() {
                return finalBloodGroup;
            }

            @Override
            public void setBloodGroup(String bloodGroup) {

            }


            @Override
            public Set<EnumRole> getRoles() {
                return finalRoles;
            }

            @Override
            public void setRoles(Set<EnumRole> roles) {

            }

            @Override
            public String getSpecialty() {
                return finalSpecialty;
            }

            @Override
            public void setSpecialty(String specialty) {

            }

            @Override
            public String getSubSpecialty() {
                return finalSubSpecialty;
            }

            @Override
            public void setSubSpecialty(String subSpecialty) {

            }
        };
    }
}
