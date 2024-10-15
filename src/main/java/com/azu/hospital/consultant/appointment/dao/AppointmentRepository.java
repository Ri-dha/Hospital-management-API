package com.azu.hospital.consultant.appointment.dao;

import com.azu.hospital.consultant.appointment.dto.AppointmentStatsDTO;
import com.azu.hospital.consultant.appointment.entity.Appointment;
import com.azu.hospital.utils.enums.appointment.AppointmentsState;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment ,Long> {



    Page<Appointment> getAllByDateAndDoctorIdOrderByNumber( Instant date, Long doctor_userId, Pageable pageable);

    Boolean existsByDateAndNumberAndDoctorId(Instant date, Integer number, Long doctor_userId);

    @Query("SELECT DATE(a.date) as apptDay, COUNT(*) as apptCount FROM Appointment a WHERE YEAR(a.date) = :year " +
            "AND MONTH(a.date) = :month And a.doctor.id = :doctorId GROUP BY apptDay")
    List<AppointmentStatsDTO> findByDateYearAndMonth(
            @Param("doctorId") Long doctorId ,
            @Param("year") Integer year ,
            @Param("month") Integer month
    );

    Boolean existsByDateAndState(Instant date, AppointmentsState state);


    List<Appointment> findAllByDate(Instant date);

    Long countByDate(Instant date);

}
