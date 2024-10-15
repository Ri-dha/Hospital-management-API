package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders;


import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dto.AnesthesiaPhysicianOrdersDto;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.request.AnesthesiaPhysicianOrdersRequest;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.services.AnesthesiaPhysicianOrdersService;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.services.AnesthesiaPhysicianOrdersUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/anesthesia-physician-orders-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class AnesthesiaPhysicianOrdersController {

  private final AnesthesiaPhysicianOrdersService service;
  private final AnesthesiaPhysicianOrdersUpdateService updateService;

  @Autowired

  public AnesthesiaPhysicianOrdersController(
          @Qualifier("AnesthesiaPhysicianOrdersService")
          AnesthesiaPhysicianOrdersService service,
          @Qualifier("AnesthesiaPhysicianOrdersUpdateService")
          AnesthesiaPhysicianOrdersUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public AnesthesiaPhysicianOrdersDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<AnesthesiaPhysicianOrdersDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllCharts(patientId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestBody AnesthesiaPhysicianOrdersRequest request,
          @RequestParam("patient_id") Long patientId
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody AnesthesiaPhysicianOrdersRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
