package com.azu.hospital.all_user_sevices.user_requests.userraequestparts;

import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class UserPersonalInfo {

  @NotBlank(message = "User name Require")
  @Size(max = 20, message = "User name Too Long")
  private String username;

  @NotBlank(message = "Password Required")
  @Size(max = 120, message = "Password Too Long")
  private String password;

  @NotNull(message = "Gender couldn't be null")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotNull(message = "martialStatus couldn't be null")
  @Enumerated(EnumType.STRING)
  private EnumMartialStatus martialStatus;

  @NotBlank(message = "Image Required")
  @NotNull(message = "Image Required")
  @NotEmpty(message = "Image Required")
  private MultipartFile image;


  public UserPersonalInfo(
          String username, String password,
          Gender gender, EnumMartialStatus martialStatus,
          MultipartFile image) {
    this.username = username;
    this.password = password;
    this.gender = gender;
    this.martialStatus = martialStatus;
    this.image = image;
  }

  public UserPersonalInfo(
          String username,
          Gender gender, EnumMartialStatus martialStatus,
          MultipartFile image) {
    this.username = username;
    this.gender = gender;
    this.martialStatus = martialStatus;
    this.image = image;
  }


  public UserPersonalInfo(String username, String password, Gender gender, EnumMartialStatus martialStatus) {
    this.username = username;
    this.password = password;
    this.gender = gender;
    this.martialStatus = martialStatus;
  }

  public UserPersonalInfo(String username, Gender gender, EnumMartialStatus martialStatus) {
    this.username = username;
    this.gender = gender;
    this.martialStatus = martialStatus;
  }
}
