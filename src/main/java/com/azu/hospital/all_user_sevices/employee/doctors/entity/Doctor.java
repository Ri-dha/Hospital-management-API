package com.azu.hospital.all_user_sevices.employee.doctors.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.utils.enums.EnumMartialStatus;
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

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;



@Entity
@Table(
        name = "doctors", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Doctor extends BaseUser implements UserDetails, AppUser {

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


    private Integer appointmentNumber;

    private String specialist;

    private String subSpecialist;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> seniorPatients;



    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "doctor")
    private Ward ward;





    public Doctor() {
    }

    public Doctor(Doctor.Builder builder) {
        this.username = builder.doctor.username;
        this.email = builder.doctor.email;
        this.password = builder.doctor.password;
        this.gender = builder.doctor.gender;
        this.birthday = builder.doctor.birthday;
        this.setMobile(builder.doctor.getMobile());
        this.martialStatus = builder.doctor.martialStatus;
        this.address = builder.doctor.address;
        this.bloodGroup = builder.doctor.bloodGroup;
        this.employeeDate = builder.doctor.employeeDate;
        this.setImage(builder.doctor.getImage());
        this.frontPersonalId = builder.doctor.frontPersonalId;
        this.backPersonalId = builder.doctor.backPersonalId;
        this.frontMedicalId = builder.doctor.frontMedicalId;
        this.backMedicalId = builder.doctor.backMedicalId;
        this.roles = builder.doctor.roles;
        this.specialist = builder.doctor.specialist;
        this.subSpecialist = builder.doctor.subSpecialist;
        this.appointmentNumber = builder.doctor.appointmentNumber = 0;
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
        private final Doctor doctor =
                new Doctor();

        public Doctor.Builder withUserName(String username) {
            doctor.username = username;
            return this;
        }

        public Doctor.Builder withEmail(String email) {
            doctor.email = email;
            return this;
        }

        public Doctor.Builder withPassword(String password) {
            doctor.password = password;
            return this;
        }

        public Doctor.Builder withEGender(Gender gender) {
            doctor.gender = gender;
            return this;
        }

        public Doctor.Builder withBirthday(String birthday) {
            doctor.birthday = birthday;
            return this;
        }

        public Doctor.Builder withMobile(String mobile) {
            doctor.setMobile(mobile);
            return this;
        }



        public Doctor.Builder withEMartialStatus(EnumMartialStatus martialStatus) {
            doctor.martialStatus = martialStatus;
            return this;
        }

        public Doctor.Builder withAddress(String address) {
            doctor.address = address;
            return this;
        }

        public Doctor.Builder withBloodGroup(String bloodGroup) {
            doctor.bloodGroup = bloodGroup;
            return this;
        }

        public Doctor.Builder withEmployeeDate(String employeeDate) {
            doctor.employeeDate = employeeDate;
            return this;
        }

        public Doctor.Builder withImage(String image) {
            doctor.setImage(image);
            return this;
        }

        public Doctor.Builder withFrontPersonalId(String frontPersonalId) {
            doctor.frontPersonalId = frontPersonalId;
            return this;
        }

        public Doctor.Builder withBackPersonalId(String backPersonalId) {
            doctor.backPersonalId = backPersonalId;
            return this;
        }

        public Doctor.Builder withFrontMedicalId(String frontMedicalId) {
            doctor.frontMedicalId = frontMedicalId;
            return this;
        }

        public Doctor.Builder withBackMedicalId(String backMedicalId) {
            doctor.backMedicalId = backMedicalId;
            return this;
        }

        public Doctor.Builder withRoles(Set<Role> roles) {
            doctor.roles = roles;
            return this;
        }

        public Doctor.Builder withSpecialist(String specialist) {
            doctor.specialist = specialist;
            return this;
        }

        public Doctor.Builder withSubSpecialist(String subSpecialist) {
            doctor.subSpecialist = subSpecialist;
            return this;
        }

        public Doctor.Builder withAppointmentNumber(Integer appointmentNumber) {
            doctor.appointmentNumber = appointmentNumber;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }


}
