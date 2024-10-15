package com.azu.hospital.hospital_info.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class HospitalInfoRequest {

        private String name;
        @NotNull(message = "image is required")
        private MultipartFile image;


        public HospitalInfoRequest() {
        }

        public HospitalInfoRequest(String name) {
                this.name = name;
        }
}
