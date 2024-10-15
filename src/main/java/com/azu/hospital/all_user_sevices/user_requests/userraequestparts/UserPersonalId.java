package com.azu.hospital.all_user_sevices.user_requests.userraequestparts;

import com.azu.hospital.Validator.ImagesValidator.ImageConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class UserPersonalId {

    @NotBlank(message = "Front Personal Id Required")
    @NotNull(message = "Front Personal Id Required")
    @NotEmpty(message = "Front Personal Id Required")
   // @ImageConstraint
    private MultipartFile frontPersonalId;

    @NotBlank(message = "Back Personal Id Required")
    @NotNull(message = "Back Personal Id Required")
    @NotEmpty(message = "Back Personal Id Required")
//    @ImageConstraint
    private MultipartFile backPersonalId;

    @NotBlank(message = "Front Medical Id Required")
    @NotNull(message = "Front Medical Id Required")
    @NotEmpty(message = "Front Medical Id Required")
//    @ImageConstraint
    private MultipartFile frontMedicalId;

    @NotBlank(message = "Back Medical Id Required")
    @NotNull(message = "Back Medical Id Required")
    @NotEmpty(message = "Back Medical Id Required")
//    @ImageConstraint
    private MultipartFile backMedicalId;


    public UserPersonalId(MultipartFile frontPersonalId,
                          MultipartFile backPersonalId,
                          MultipartFile frontMedicalId,
                          MultipartFile backMedicalId
    ) {
        this.frontPersonalId = frontPersonalId;
        this.backPersonalId = backPersonalId;
        this.frontMedicalId = frontMedicalId;
        this.backMedicalId = backMedicalId;
    }


}
