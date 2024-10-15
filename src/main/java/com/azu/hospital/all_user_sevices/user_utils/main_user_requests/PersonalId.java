package com.azu.hospital.all_user_sevices.user_utils.main_user_requests;

import org.springframework.web.multipart.MultipartFile;

public interface PersonalId {
    MultipartFile getFrontPersonalId();

    void setFrontPersonalId(MultipartFile frontPersonalId);

    MultipartFile getBackPersonalId();

    void setBackPersonalId(MultipartFile backPersonalId);

    MultipartFile getFrontMedicalId();

    void setFrontMedicalId(MultipartFile frontMedicalId);

    MultipartFile getBackMedicalId();

    void setBackMedicalId(MultipartFile backMedicalId);
}
