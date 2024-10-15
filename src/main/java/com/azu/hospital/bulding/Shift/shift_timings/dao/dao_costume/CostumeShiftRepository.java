package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_costume;

import com.azu.hospital.bulding.Shift.shift_timings.entity.CostumeShift;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumeShiftRepository extends JpaRepository<CostumeShift, Long> {


    @Query("SELECT b FROM CostumeShift b WHERE b.ward.wardId = :wardId AND (:role IS NULL OR b.role = :role)")
    List<CostumeShift> findAllByWardWardId(@Param("wardId") Long wardId, @Param("role") EnumRole role);
}
