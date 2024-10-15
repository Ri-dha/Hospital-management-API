package com.azu.hospital.bulding.hospitalSettings.config;

import com.azu.hospital.bulding.hospitalSettings.dao.HospitalSettings;
import com.azu.hospital.bulding.hospitalSettings.dao.HospitalSettingsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HospitalSettingsConfig implements ApplicationRunner {

  private final HospitalSettingsJpaRepository repository;

  @Autowired
  public HospitalSettingsConfig(HospitalSettingsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (repository.count() == 0){
      repository.save(
              new HospitalSettings.Builder()
                      .withHospitalName("White Castle")
                      .build()
      );
    }
  }
}
