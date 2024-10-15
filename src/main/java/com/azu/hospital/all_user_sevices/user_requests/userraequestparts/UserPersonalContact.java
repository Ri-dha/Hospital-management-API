package com.azu.hospital.all_user_sevices.user_requests.userraequestparts;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserPersonalContact {

    @NotBlank(message = "Email Required")
    @Size(max = 50, message = "Email Too Long")
    @Email(
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Email Not Valid"
    )
    private String email;

    @NotBlank(message = "Phone Number Required")
    @NotNull(message = "Phone Number Required")
    @NotEmpty(message = "Phone Number Required")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Phone number not valid")
// TODO: 10/26/2023 this regex not working
    private String mobile;

    @NotBlank(message = "Address Required")
    @NotNull(message = "Address Required")
    @NotEmpty(message = "Address Required")
    private String address;


    public UserPersonalContact(String email, String mobile, String address) {
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }
}
