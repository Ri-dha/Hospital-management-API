package com.azu.hospital.bulding.Shift.dao;

import com.azu.hospital.bulding.Shift.entity.Shift;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
