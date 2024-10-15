package com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder;

import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.PersonalInfo;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class PersonalInfoBuilder {
    private String username;
    private String password;
    private Gender gender;
    private EnumMartialStatus martialStatus;
    @Nullable
    private MultipartFile image;


    public PersonalInfoBuilder() {
    };

    public PersonalInfoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public PersonalInfoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public PersonalInfoBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PersonalInfoBuilder withMartialStatus(EnumMartialStatus martialStatus) {
        this.martialStatus = martialStatus;
        return this;
    }

    public PersonalInfoBuilder withImage(MultipartFile image) {
        this.image = image;
        return this;
    }


    public PersonalInfo build() {
        final String finalUsername = this.username;
        final String finalPassword = this.password;
        final Gender finalGender = this.gender;
        final EnumMartialStatus finalMartialStatus = this.martialStatus;

        final MultipartFile finalImage = this.image;

        return new PersonalInfo() {
            @Override
            public String getUsername() {
                return finalUsername;
            }

            @Override
            public void setUsername(String username) {
                // This method intentionally left empty
            }

            @Override
            public String getPassword() {
                return finalPassword;
            }

            @Override
            public void setPassword(String password) {
                // This method intentionally left empty
            }

            @Override
            public Gender getGender() {
                return finalGender;
            }

            @Override
            public void setGender(Gender gender) {
                // This method intentionally left empty
            }

            @Override
            public EnumMartialStatus getMartialStatus() {
                return finalMartialStatus;
            }

            @Override
            public void setMartialStatus(EnumMartialStatus martialStatus) {
                // This method intentionally left empty
            }

            @Override
            public MultipartFile getImage() {
                return finalImage;
            }

            @Override
            public void setImage(MultipartFile image) {
                // This method intentionally left empty
            }
        };
    }
}


