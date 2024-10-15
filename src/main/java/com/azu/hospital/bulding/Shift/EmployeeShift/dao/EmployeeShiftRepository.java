package com.azu.hospital.bulding.Shift.EmployeeShift.dao;

import com.azu.hospital.bulding.Shift.EmployeeShift.entity.EmployeeShift;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {


    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN TRUE ELSE FALSE END FROM EmployeeShift n WHERE n.user.id = :userId AND " +
            "n" +
            ".shift.id = :shiftId")
    Boolean existsByUserIdAndShiftId(@Param("userId") Long userId, @Param("shiftId") Long shiftId);


    @Query("SELECT n FROM EmployeeShift n WHERE n.user.id = :userId " +
            "AND EXTRACT(YEAR FROM CAST(n.createdAt AS localdate)) <= :#{#date.year} AND " +
            "EXTRACT(MONTH FROM CAST(n.createdAt AS localdate)) <= :#{#date.monthValue} AND " +
            "EXTRACT(DAY FROM CAST(n.createdAt AS localdate)) <= :#{#date.dayOfMonth}"
    )
    List<EmployeeShift> getUserShiftsByNurseId(@Param("userId") Long userId , @Param("date") LocalDate date);



    @Query("SELECT MAX(n.day) FROM EmployeeShift n WHERE n.user.id = :userId AND n.shift.id = :shiftId")
    Integer getMaxDayByUserIdAndShiftId(@Param("userId") Long userId, @Param("shiftId") Long shiftId);


    @Query("SELECT n.day FROM EmployeeShift n WHERE n.user.id = :userId ")
    List<Integer> getDaysByUserIdAndShiftId(Long userId);
}
