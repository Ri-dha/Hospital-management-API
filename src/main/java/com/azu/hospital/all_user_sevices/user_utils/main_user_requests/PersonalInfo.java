package com.azu.hospital.all_user_sevices.user_utils.main_user_requests;

import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;


public interface PersonalInfo {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    Gender getGender();

    void setGender(Gender gender);

    EnumMartialStatus getMartialStatus();

    void setMartialStatus(EnumMartialStatus martialStatus);

    MultipartFile getImage();

    void setImage(MultipartFile image);
}
