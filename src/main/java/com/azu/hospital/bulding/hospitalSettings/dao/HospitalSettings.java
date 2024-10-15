package com.azu.hospital.bulding.hospitalSettings.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "hospital_settings")
@Getter
@Setter
public class HospitalSettings {
  @Id
  @SequenceGenerator(
          name = "hospital_settings_sequence",
          sequenceName = "hospital_settings_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "hospital_settings_sequence"
  )
  private Long id;

  private String hospitalName;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  public HospitalSettings() {
  }
  public HospitalSettings(Builder builder) {
    this.hospitalName = builder.settings.hospitalName;
  }

  public HospitalSettings(Long id, String hospitalName) {
    this.id = id;
    this.hospitalName = hospitalName;
  }

  public static class Builder{
    HospitalSettings settings = new HospitalSettings();

    public Builder withHospitalName(String name){
      settings.hospitalName = name;
      return this;
    }
    public HospitalSettings build(){
      return new HospitalSettings(this);
    }
  }

  @PrePersist
  public void onCreate(){
    this.createdAt = Instant.now();
  }

  @PreUpdate
  public void onUpdate(){
    this.updatedAt = Instant.now();
  }
}
