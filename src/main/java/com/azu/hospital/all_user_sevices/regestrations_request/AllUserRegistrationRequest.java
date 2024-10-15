package com.azu.hospital.all_user_sevices.regestrations_request;

import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.*;
import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder.*;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.Gender;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class AllUserRegistrationRequest implements PersonalInfo, PersonalContact, PersonalDate, PersonalJobInfo, PersonalId {

    private final PersonalInfo personalInfo;
    private final PersonalContact contactInfo;
    private final PersonalJobInfo jobInfo;
    private final PersonalDate personalDate;
    private final PersonalId personalId;


    @JsonCreator
    public AllUserRegistrationRequest(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("martialStatus") EnumMartialStatus martialStatus,
            @JsonProperty("image") MultipartFile image,
            @JsonProperty("email") String email,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("address") String address,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("employeeDate") String employeeDate,
            @JsonProperty("frontPersonalId") MultipartFile frontPersonalId,
            @JsonProperty("backPersonalId") MultipartFile backPersonalId,
            @JsonProperty("frontMedicalId") MultipartFile frontMedicalId,
            @JsonProperty("backMedicalId") MultipartFile backMedicalId,
            @JsonProperty("bloodGroup") String bloodGroup,
            @JsonProperty("roles") Set<EnumRole> roles,
            @JsonProperty("specialist") String specialist,
            @JsonProperty("subSpecialty") String subSpecialty
    ) {
        this.personalInfo = new PersonalInfoBuilder()
                .withUsername(username)
                .withPassword(password)
                .withGender(gender)
                .withMartialStatus(martialStatus)
                .withImage(image)
                .build();
        this.contactInfo = new PersonalContactBuilder()
                .withEmail(email)
                .withMobile(mobile)
                .withAddress(address)
                .build();
        this.jobInfo = new PersonalJobInfoBuilder()
                .withSpecialty(specialist)
                .withSubSpecialty(subSpecialty)
                .withRoles(roles)
                .withBloodGroup(bloodGroup)

                .build();
        this.personalDate = new PersonalDateBuilder()
                .withEmployeeDate(employeeDate)
                .withBirthday(birthday)
                .build();
        this.personalId = new PersonalIdBuilder()
                .withFrontMedicalId(frontMedicalId)
                .withBackMedicalId(backMedicalId)
                .withFrontPersonalId(frontPersonalId)
                .withBackPersonalId(backPersonalId)
                .build();
    }


    @Override
    public String getEmail() {
        return contactInfo.getEmail();
    }

    @Override
    public void setEmail(String email) {
      this.contactInfo.setEmail(email);
    }

    @Override
    public String getMobile() {
        return contactInfo.getMobile();
    }

    @Override
    public void setMobile(String mobile) {
        this.contactInfo.setMobile(mobile);
    }

    @Override
    public String getAddress() {
        return contactInfo.getAddress();
    }

    @Override
    public void setAddress(String address) {
        this.contactInfo.setAddress(address);
    }

    @Override
    public String getBirthday() {
        return personalDate.getBirthday();
    }

    @Override
    public void setBirthday(String birthday) {
        this.personalDate.setBirthday(birthday);
    }

    @Override
    public String getEmployeeDate() {
        return personalDate.getEmployeeDate();
    }

    @Override
    public void setEmployeeDate(String employeeDate) {
        this.personalDate.setEmployeeDate(employeeDate);
    }

    @Override
    public MultipartFile getFrontPersonalId() {
        return personalId.getFrontPersonalId();
    }

    @Override
    public void setFrontPersonalId(MultipartFile frontPersonalId) {
        this.personalId.setFrontPersonalId(frontPersonalId);
    }

    @Override
    public MultipartFile getBackPersonalId() {
        return personalId.getBackPersonalId();
    }

    @Override
    public void setBackPersonalId(MultipartFile backPersonalId) {
        this.personalId.setBackPersonalId(backPersonalId);
    }

    @Override
    public MultipartFile getFrontMedicalId() {
        return personalId.getFrontMedicalId();
    }

    @Override
    public void setFrontMedicalId(MultipartFile frontMedicalId) {
        this.personalId.setFrontMedicalId(frontMedicalId);
    }

    @Override
    public MultipartFile getBackMedicalId() {
        return personalId.getBackMedicalId();
    }

    @Override
    public void setBackMedicalId(MultipartFile backMedicalId) {
        this.personalId.setBackMedicalId(backMedicalId);
    }

    @Override
    public String getUsername() {
        return personalInfo.getUsername();
    }

    @Override
    public void setUsername(String username) {
        this.personalInfo.setUsername(username);
    }

    @Override
    public String getPassword() {
        return personalInfo.getPassword();
    }

    @Override
    public void setPassword(String password) {
        this.personalInfo.setPassword(password);
    }

    @Override
    public Gender getGender() {
        return personalInfo.getGender();
    }

    @Override
    public void setGender(Gender gender) {
        this.personalInfo.setGender(gender);
    }

    @Override
    public EnumMartialStatus getMartialStatus() {
        return personalInfo.getMartialStatus();
    }

    @Override
    public void setMartialStatus(EnumMartialStatus martialStatus) {
        this.personalInfo.setMartialStatus(martialStatus);
    }

    @Override
    public MultipartFile getImage() {
        return personalInfo.getImage();
    }

    @Override
    public void setImage(MultipartFile image) {
      this.personalInfo.setImage(image);
    }

    @Override
    public String getBloodGroup() {
        return jobInfo.getBloodGroup();
    }

    @Override
    public void setBloodGroup(String bloodGroup) {
            this.jobInfo.setBloodGroup(bloodGroup);
    }


    @Override
    public Set<EnumRole> getRoles() {
        return jobInfo.getRoles();
    }

    @Override
    public void setRoles(Set<EnumRole> roles) {
        this.jobInfo.setRoles(roles);
    }

    @Override
    public String getSpecialty() {
        return jobInfo.getSpecialty();
    }

    @Override
    public void setSpecialty(String specialist) {
        this.jobInfo.setSpecialty(specialist);
    }

    @Override
    public String getSubSpecialty() {
        return jobInfo.getSubSpecialty();
    }

    @Override
    public void setSubSpecialty(String subSpecialty) {
     this.jobInfo.setSubSpecialty(subSpecialty);
    }



}
