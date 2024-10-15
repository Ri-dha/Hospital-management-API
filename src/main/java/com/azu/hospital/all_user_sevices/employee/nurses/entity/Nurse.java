package com.azu.hospital.all_user_sevices.employee.nurses.entity;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;



@Entity
@Table(
        name = "nurses", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Nurse extends BaseUser implements UserDetails, AppUser {

    @NotNull(message = "Gender couldn't be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Birthday require")
    @NotNull(message = "Birthday require")
    @NotEmpty(message = "Birthday require")
    @DatePattern(message = "Error Data formatting, Expected formatting: YYYY-MM-DD ")
    private String birthday;

    @Transient
    private Integer age;


    @NotNull(message = "Martial Status couldn't be null")
    @Enumerated(EnumType.STRING)
    private EnumMartialStatus martialStatus;

    @NotBlank(message = "Address Require")
    @NotNull(message = "Address Require")
    @NotEmpty(message = "Address Require")
    private String address;

    @NotBlank(message = "blood Group Require")
    @NotNull(message = "blood Group Require")
    @NotEmpty(message = "blood Group Require")
    private String bloodGroup;

    @NotBlank(message = "Employee Date require")
    @NotNull(message = "Employee Date require")
    @NotEmpty(message = "Employee Date require")
    @DatePattern(message = "Error Data formatting, Expected formatting: YYYY-MM-DD ")
    private String employeeDate;

    @NotBlank(message = "Phone Number Require")
    @NotNull(message = "Phone Number Require")
    @NotEmpty(message = "Phone Number Require")
    private String mobile;

    private String image;

    private String frontPersonalId;

    private String backPersonalId;

    private String frontMedicalId;

    private String backMedicalId;


    private String specialist;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nurse")
    private Ward ward;

    public Nurse() {
    }

    public Nurse(Builder builder) {
        this.username = builder.nurse.username;
        this.email = builder.nurse.email;
        this.password = builder.nurse.password;
        this.gender = builder.nurse.gender;
        this.birthday = builder.nurse.birthday;
        this.setMobile(builder.nurse.getMobile());
        this.martialStatus = builder.nurse.martialStatus;
        this.address = builder.nurse.address;
        this.bloodGroup = builder.nurse.bloodGroup;
        this.employeeDate = builder.nurse.employeeDate;
        this.setImage(builder.nurse.getImage()) ;
        this.frontPersonalId = builder.nurse.frontPersonalId;
        this.backPersonalId = builder.nurse.backPersonalId;
        this.frontMedicalId = builder.nurse.frontMedicalId;
        this.backMedicalId = builder.nurse.backMedicalId;
        this.roles = builder.nurse.roles;
        this.specialist = builder.nurse.specialist;
        this.setAccountNonExpired(true);
        this.setEnabled(true);
        this.setAccountNonLocked(true);
        this.setCredentialsNonExpired(true);


    }

    public Integer getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate birthday = LocalDate.parse(this.birthday, formatter);

        return Period.between(birthday, LocalDate.now()).getYears();
    }




    public static class Builder {
        private final Nurse nurse = new Nurse();

        public Builder withUserName(String username) {
            nurse.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            nurse.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            nurse.password = password;
            return this;
        }

        public Builder withEGender(Gender gender) {
            nurse.gender = gender;
            return this;
        }

        public Builder withBirthday(String birthday) {
            nurse.birthday = birthday;
            return this;
        }

        public Builder withMobile(String mobile) {
            nurse.setMobile(mobile);
            return this;
        }


        public Builder withEMartialStatus(EnumMartialStatus martialStatus) {
            nurse.martialStatus = martialStatus;
            return this;
        }

        public Builder withAddress(String address) {
            nurse.address = address;
            return this;
        }

        public Builder withBloodGroup(String bloodGroup) {
            nurse.bloodGroup = bloodGroup;
            return this;
        }

        public Builder withEmployeeDate(String employeeDate) {
            nurse.employeeDate = employeeDate;
            return this;
        }

        public Builder withImage(String image) {
            nurse.setImage(image);
            return this;
        }

        public Builder withFrontPersonalId(String frontPersonalId) {
            nurse.frontPersonalId = frontPersonalId;
            return this;
        }

        public Builder withBackPersonalId(String backPersonalId) {
            nurse.backPersonalId = backPersonalId;
            return this;
        }

        public Builder withFrontMedicalId(String frontMedicalId) {
            nurse.frontMedicalId = frontMedicalId;
            return this;
        }

        public Builder withBackMedicalId(String backMedicalId) {
            nurse.backMedicalId = backMedicalId;
            return this;
        }

        public Builder withRoles(Set<Role> roles) {
            nurse.roles = roles;
            return this;
        }

        public Builder withSpecialist(String specialist) {
            nurse.specialist = specialist;
            return this;
        }


        public Nurse build() {
            return new Nurse(this);
        }
    }


}
