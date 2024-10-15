package com.azu.hospital.all_user_sevices.employee.permanent.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;



@Entity
@Table(
        name = "permanents", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")

})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Permanent extends BaseUser implements UserDetails, AppUser {


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

    @NotBlank(message = "Phone Number Require")
    @NotNull(message = "Phone Number Require")
    @NotEmpty(message = "Phone Number Require")
    @Column(unique = true)
    private String mobile;


    private String image;

    @NotBlank(message = "Employee Date require")
    @NotNull(message = "Employee Date require")
    @NotEmpty(message = "Employee Date require")
    @DatePattern(message = "Error Data formatting, Expected formatting: YYYY-MM-DD ")
    private String employeeDate;

    private String frontPersonalId;

    private String backPersonalId;

    private String frontMedicalId;

    private String backMedicalId;



    private String specialist;


    @OneToMany()
    private List<Patient> permanentPatients;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostAnestheticEvaluationChart> postAnestheticEvaluationCharts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BurnDiagramChart> burnDiagramCharts;

    public Permanent() {
    }

    public Permanent(Builder builder) {
        this.username = builder.permanent.username;
        this.email = builder.permanent.email;
        this.password = builder.permanent.password;
        this.gender = builder.permanent.gender;
        this.birthday = builder.permanent.birthday;
        this.setMobile(builder.permanent.getMobile());
        this.martialStatus = builder.permanent.martialStatus;
        this.address = builder.permanent.address;
        this.bloodGroup = builder.permanent.bloodGroup;
        this.employeeDate = builder.permanent.employeeDate;
        this.setImage(builder.permanent.getImage());
        this.frontPersonalId = builder.permanent.frontPersonalId;
        this.backPersonalId = builder.permanent.backPersonalId;
        this.frontMedicalId = builder.permanent.frontMedicalId;
        this.backMedicalId = builder.permanent.backMedicalId;
        this.roles = builder.permanent.roles;
        this.specialist = builder.permanent.specialist;
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
        private final Permanent permanent =
                new Permanent();

        public Builder withUserName(String username) {
            permanent.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            permanent.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            permanent.password = password;
            return this;
        }

        public Builder withEGender(Gender gender) {
            permanent.gender = gender;
            return this;
        }

        public Builder withBirthday(String birthday) {
            permanent.birthday = birthday;
            return this;
        }

        public Builder withMobile(String mobile) {
            permanent.setMobile(mobile);
            return this;
        }



        public Builder withEMartialStatus(EnumMartialStatus martialStatus) {
            permanent.martialStatus = martialStatus;
            return this;
        }

        public Builder withAddress(String address) {
            permanent.address = address;
            return this;
        }

        public Builder withBloodGroup(String bloodGroup) {
            permanent.bloodGroup = bloodGroup;
            return this;
        }

        public Builder withEmployeeDate(String employeeDate) {
            permanent.employeeDate = employeeDate;
            return this;
        }

        public Builder withImage(String image) {
            permanent.setImage(image);
            return this;
        }

        public Builder withFrontPersonalId(String frontPersonalId) {
            permanent.frontPersonalId = frontPersonalId;
            return this;
        }

        public Builder withBackPersonalId(String backPersonalId) {
            permanent.backPersonalId = backPersonalId;
            return this;
        }

        public Builder withFrontMedicalId(String frontMedicalId) {
            permanent.frontMedicalId = frontMedicalId;
            return this;
        }

        public Builder withBackMedicalId(String backMedicalId) {
            permanent.backMedicalId = backMedicalId;
            return this;
        }

        public Builder withRoles(Set<Role> roles) {
            permanent.roles = roles;
            return this;
        }

        public Builder withSpecialist(String specialist) {
            permanent.specialist = specialist;
            return this;
        }




        public Permanent build() {
            return new Permanent(this);
        }
    }


}
