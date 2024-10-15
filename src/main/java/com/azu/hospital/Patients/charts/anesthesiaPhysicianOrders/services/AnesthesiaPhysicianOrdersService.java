package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dto.AnesthesiaPhysicianOrdersDto;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dto.AnesthesiaPhysicianOrdersDtoMapper;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.request.AnesthesiaPhysicianOrdersRequest;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.utils.AnesthesiaPhysicianOrdersRequestToChart;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;
import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dao.AnesthesiaPhysicianOrdersDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.*;

@Service("AnesthesiaPhysicianOrdersService")
public class AnesthesiaPhysicianOrdersService {
  private final AnesthesiaPhysicianOrdersDao chartDao;
  private final AllPatientChartsDao allPatientChartsDao;
  private final AnesthesiaPhysicianOrdersDtoMapper dtoMapper;
  private final PatientDao patientDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public AnesthesiaPhysicianOrdersService(
          @Qualifier("AnesthesiaPhysicianOrdersJpaDataAccess")
          AnesthesiaPhysicianOrdersDao chartDao,
          @Qualifier("AllPatientChartsJpaDataAccess")
          AllPatientChartsDao allPatientChartsDao,
          AnesthesiaPhysicianOrdersDtoMapper dtoMapper,
          @Qualifier("patientRepo")
          PatientDao patientDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.allPatientChartsDao = allPatientChartsDao;
    this.dtoMapper = dtoMapper;
    this.patientDao = patientDao;
      this.messageReturn = messageReturn;
  }

  public AnesthesiaPhysicianOrdersDto getChartById(Long chartId) {
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    AnesthesiaPhysicianOrders chart = chartDao.findChartId(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("AnesthesiaPhysicianOrdersChartNotFound")+" "+chartId+" "
                    )
            );
    return dtoMapper.chartToDto(chart);
  }

  public DtoForReturnIdOnly createNewChart(
          Long patientId,
          AnesthesiaPhysicianOrdersRequest request
  ) {
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException
                            (
                                    messages.getString("patientNotFound")+" "+patientId
                            )
            );


    if (!patient.getIsHasMedicalHistory())
        throw new ResourceNotFoundException("Patient Doesn't Have Medical History");

    AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
            .orElseGet(
                    () -> {
                      AllPatientChart c = new AllPatientChart();
                      c.setPatient(patient);
                      return allPatientChartsDao.addNewChart(c);
                    }
            );

    AnesthesiaPhysicianOrders chart = AnesthesiaPhysicianOrdersRequestToChart.requestToChart(request);

    chart.setPatient(patient);

    AnesthesiaPhysicianOrders saved = chartDao.createNewChart(chart);

    allCharts.setChartType(ChartTypes.ANESTHESIA_PHYSICIAN_ORDERS);
    allPatientChartsDao.updateCharts(allCharts);

    return dtoMapper.dtoForReturnIdOnly(saved);
  }

  public List<AnesthesiaPhysicianOrdersDto> getAllCharts(Long patientId) {

    return chartDao
            .getAllAnesthesiaPhysicianOrdersByPatientId(patientId)
            .stream()
            .map(dtoMapper::chartToDto)
            .toList();
  }
}
