package com.azu.hospital.all_user_sevices.user_entity;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
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
import java.util.Set;




@Entity
@Table(
        name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class User extends BaseUser implements UserDetails, AppUser{

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

    @NotBlank(message = "Phone Number Require")
    @NotNull(message = "Phone Number Require")
    @NotEmpty(message = "Phone Number Require")
    private String mobile;

    private String image;

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



    @OneToOne(cascade = CascadeType.ALL)
    private Employee  employee;


    private String frontPersonalId;

    private String backPersonalId;

    private String frontMedicalId;

    private String backMedicalId;

    private String specialist;



    public User() {
    }

    public User(Builder builder) {
        this.username = builder.user.username;
        this.email = builder.user.email;
        this.password = builder.user.password;
        this.gender = builder.user.gender;
        this.birthday = builder.user.birthday;
        this.setMobile( builder.user.getMobile());
        this.martialStatus = builder.user.martialStatus;
        this.address = builder.user.address;
        this.bloodGroup = builder.user.bloodGroup;
        this.employeeDate = builder.user.employeeDate;
        this.setImage(builder.user.getImage());
        this.frontPersonalId = builder.user.frontPersonalId;
        this.backPersonalId = builder.user.backPersonalId;
        this.frontMedicalId = builder.user.frontMedicalId;
        this.backMedicalId = builder.user.backMedicalId;
        this.roles = builder.user.roles;
        this.specialist = builder.user.specialist;
        this.setAccountNonExpired(true);
        this.setEnabled(true);
        this.setAccountNonLocked(true);
        this.setCredentialsNonExpired(true);

    }


    public String getUsernameByUserName() {
        return username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public EnumMartialStatus getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(EnumMartialStatus martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(String employeeDate) {
        this.employeeDate = employeeDate;
    }

    public String getFrontPersonalId() {
        return frontPersonalId;
    }

    public void setFrontPersonalId(String frontPersonalId) {
        this.frontPersonalId = frontPersonalId;
    }

    public String getBackPersonalId() {
        return backPersonalId;
    }

    public void setBackPersonalId(String backPersonalId) {
        this.backPersonalId = backPersonalId;
    }

    public String getFrontMedicalId() {
        return frontMedicalId;
    }

    public void setFrontMedicalId(String frontMedicalId) {
        this.frontMedicalId = frontMedicalId;
    }

    public String getBackMedicalId() {
        return backMedicalId;
    }

    public void setBackMedicalId(String backMedicalId) {
        this.backMedicalId = backMedicalId;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }



    public Integer getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate birthday = LocalDate.parse(this.birthday, formatter);

        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public static class Builder {
        private final User user = new User();

        public Builder withUserName(String username) {
            user.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withEGender(Gender gender) {
            user.gender = gender;
            return this;
        }

        public Builder withBirthday(String birthday) {
            user.birthday = birthday;
            return this;
        }

        public Builder withMobile(String mobile) {
            user.setMobile(mobile);
            return this;
        }



        public Builder withEMartialStatus(EnumMartialStatus martialStatus) {
            user.martialStatus = martialStatus;
            return this;
        }

        public Builder withAddress(String address) {
            user.address = address;
            return this;
        }

        public Builder withBloodGroup(String bloodGroup) {
            user.bloodGroup = bloodGroup;
            return this;
        }

        public Builder withEmployeeDate(String employeeDate) {
            user.employeeDate = employeeDate;
            return this;
        }

        public Builder withImage(String image) {
            user.setImage(image);
            return this;
        }

        public Builder withFrontPersonalId(String frontPersonalId) {
            user.frontPersonalId = frontPersonalId;
            return this;
        }

        public Builder withBackPersonalId(String backPersonalId) {
            user.backPersonalId = backPersonalId;
            return this;
        }

        public Builder withFrontMedicalId(String frontMedicalId) {
            user.frontMedicalId = frontMedicalId;
            return this;
        }

        public Builder withBackMedicalId(String backMedicalId) {
            user.backMedicalId = backMedicalId;
            return this;
        }


        public Builder withRoles(Set<Role> roles) {
            user.roles = roles;
            return this;
        }

        public Builder withSpecialist(String specialist) {
            user.specialist = specialist;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
