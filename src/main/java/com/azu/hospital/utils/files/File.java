package com.azu.hospital.utils.files;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {

    @Id
    @SequenceGenerator(name = "file_seq" , sequenceName = "file_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Image name cannot be blank")
    private String fileType;

    @NotBlank(message = "Image reference cannot be blank")
    private String fileReference;


    @NotBlank(message = "Image URL cannot be blank")
    private String fileUrl;

    public File(String fileType, String fileReference, String fileUrl) {
        this.fileType = fileType;
        this.fileReference = fileReference;
        this.fileUrl = fileUrl;
    }

    public File() {
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}