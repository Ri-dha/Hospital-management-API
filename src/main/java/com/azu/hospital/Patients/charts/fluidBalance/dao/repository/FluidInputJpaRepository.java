package com.azu.hospital.Patients.charts.fluidBalance.dao.repository;

import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidIntake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FluidInputJpaRepository extends JpaRepository<FluidIntake, Long> {
}
