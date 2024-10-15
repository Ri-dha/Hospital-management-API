package com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder;

import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.PersonalId;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class PersonalIdBuilder {


    private MultipartFile frontPersonalId;

    private MultipartFile backPersonalId;

    private MultipartFile frontMedicalId;

    private MultipartFile backMedicalId;

    public PersonalIdBuilder() {
    };

    public PersonalIdBuilder withFrontPersonalId(MultipartFile frontPersonalId) {
        this.frontPersonalId = frontPersonalId;
        return this;
    }

    public PersonalIdBuilder withBackPersonalId(MultipartFile backPersonalId) {
        this.backPersonalId = backPersonalId;
        return this;
    }

    public PersonalIdBuilder withFrontMedicalId(MultipartFile frontMedicalId) {
        this.frontMedicalId = frontMedicalId;
        return this;
    }
    public PersonalIdBuilder withBackMedicalId(MultipartFile backMedicalId) {
        this.backMedicalId = backMedicalId;
        return this;
    }

    public PersonalId build() {

        final MultipartFile finalFrontPersonalId = this.frontPersonalId;

        final MultipartFile finalBackPersonalId = this.backPersonalId;

        final MultipartFile finalFrontMedicalId = this.frontMedicalId;

        final MultipartFile finalBackMedicalId = this.backMedicalId;

        return new PersonalId() {
            @Override

            public MultipartFile getFrontPersonalId() {
                return finalFrontPersonalId;
            }

            @Override
            public void setFrontPersonalId(MultipartFile frontPersonalId) {
                // This method intentionally left empty
            }

            @Override

            public MultipartFile getBackPersonalId() {
                return finalBackPersonalId;
            }

            @Override
            public void setBackPersonalId(MultipartFile backPersonalId) {
                // This method intentionally left empty
            }

            @Override

            public MultipartFile getFrontMedicalId() {
                return finalFrontMedicalId;
            }

            @Override
            public void setFrontMedicalId(MultipartFile frontMedicalId) {
                // This method intentionally left empty
            }

            @Override

            public MultipartFile getBackMedicalId() {
                return finalBackMedicalId;
            }

            @Override
            public void setBackMedicalId(MultipartFile backMedicalId) {
                // This method intentionally left empty
            }
        };
    }
}

