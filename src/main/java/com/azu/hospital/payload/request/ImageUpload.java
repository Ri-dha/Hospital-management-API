package com.azu.hospital.payload.request;


public final class ImageUpload {
    private final String image;
    private final String frontMedicalId;
    private final String frontPersonalId;
    private final String backMedicalId;
    private final String backPersonalId;

    public ImageUpload(
            String image,
            String frontMedicalId,
            String frontPersonalId,
            String backMedicalId,
            String backPersonalId
    ) {
        this.image = image;
        this.frontMedicalId = frontMedicalId;
        this.frontPersonalId = frontPersonalId;
        this.backMedicalId = backMedicalId;
        this.backPersonalId = backPersonalId;
    }

    public String image() {
        return image;
    }

    public String frontMedicalId() {
        return frontMedicalId;
    }

    public String frontPersonalId() {
        return frontPersonalId;
    }

    public String backMedicalId() {
        return backMedicalId;
    }

    public String backPersonalId() {
        return backPersonalId;
    }


}
