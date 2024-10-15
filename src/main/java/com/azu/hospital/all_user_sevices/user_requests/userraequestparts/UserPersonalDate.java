package com.azu.hospital.all_user_sevices.user_requests.userraequestparts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserPersonalDate {

    @NotBlank(message = "Birth Day Required")
    @NotNull(message = "Birth Day Required")
    @NotEmpty(message = "Birth Day Required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String birthday;

    @NotBlank(message = "Date Of Employee Required")
    @NotNull(message = "Date Of Employee Required")
    @NotEmpty(message = "Date Of Employee Required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String employeeDate;


    public UserPersonalDate(String birthday, String employeeDate) {
        this.birthday = birthday;
        this.employeeDate = employeeDate;
    }
}
