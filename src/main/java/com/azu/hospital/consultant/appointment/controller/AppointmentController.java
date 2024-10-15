package com.azu.hospital.consultant.appointment.controller;

import com.azu.hospital.consultant.appointment.request.CreateNewAppointmentRequest;
import com.azu.hospital.consultant.appointment.services.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Consultant Appointment" , description = "Appointment Routes")
@RestController
@RequestMapping("api/v1/appointments")
@CrossOrigin
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    private ResponseEntity<?> createNewAppointment(@Valid @ModelAttribute CreateNewAppointmentRequest request){
          return ResponseEntity.ok(appointmentService.createNewAppointment(request));
    }

    @GetMapping("/get")
    private ResponseEntity<?> getAppointment(
        @RequestParam Long doctorId,
        @RequestParam String date,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "15") Integer size
    ){

        Pageable pageable = PageRequest.of(page,size);

          return ResponseEntity
                  .status(200)
                  .body(appointmentService.getAppointmentByDoctorIdAndDate(doctorId,date,pageable));
    }
   @GetMapping("/get-dates")
    private ResponseEntity<?> getAppointmentDates(
            @RequestParam Long doctorId,
            @RequestParam String date
   ){
          return ResponseEntity.ok(appointmentService.getAppointmentDates(doctorId,date));
    }
   @GetMapping("/get-valid-appointment")
    private ResponseEntity<?> getValidAppointment(
            @RequestParam Long doctorId,
            @RequestParam String date
   ){
          return ResponseEntity.ok(appointmentService.getValidAppointment(doctorId,date));
    }

    @PostMapping("/change-state")
    private ResponseEntity<?> changeStateAppointment(
            @RequestParam Long appointmentId,
            @RequestParam(defaultValue = "false") Boolean isCancel
   ){
          return ResponseEntity.ok(appointmentService.changeState(appointmentId , isCancel));
    }



}
