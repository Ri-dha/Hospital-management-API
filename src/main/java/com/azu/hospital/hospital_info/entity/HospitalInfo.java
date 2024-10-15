package com.azu.hospital.hospital_info.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;


@Entity
@Getter
@Setter
@Table(name = "hospital_info")
public class HospitalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "base_user_id_seq",
            sequenceName = "base_user_id_seq"
    )
    private Long id;

    private String name;

    private String image;


    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false )
    private Instant updatedAt;


    public HospitalInfo() {
    }

    public HospitalInfo(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public HospitalInfo(String name, MultipartFile image) {
        this.name = name;
        this.image = image.getOriginalFilename();
    }

    public HospitalInfo(String name) {
        this.name = name;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
