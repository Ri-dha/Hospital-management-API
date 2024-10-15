package com.azu.hospital.utils.image;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @SequenceGenerator(name = "image_seq" , sequenceName = "image_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Image name cannot be blank")
    private String imageType;

    @NotBlank(message = "Image reference cannot be blank")
    private String imageReference;


    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    public Image() {
    }

    public Image(String imageType, String imageReference, String imageUrl) {
        this.imageType = imageType;
        this.imageReference = imageReference;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}