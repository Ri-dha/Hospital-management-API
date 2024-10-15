package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dto;


import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartDtoMapperInterface;
import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnesthesiaPhysicianOrdersDtoMapper implements BaseChartDtoMapperInterface {

    public AnesthesiaPhysicianOrdersDto chartToDto(AnesthesiaPhysicianOrders chart) {
        return Optional.ofNullable(chart)
                .map(c -> new AnesthesiaPhysicianOrdersDto(
                        c.getId(),
                        c.getChartName(),
                        c.getAnesthesiologistName(),
                        c.getOxygen(),
                        c.getIvPerHour(),
                        c.getIvRun(),
                        c.getDcIv(),
                        c.getToradol(),
                        c.getToradolMg(),
                        c.getOtherParenteral(),
                        c.getMotrin(),
                        c.getOtherOral(),
                        c.getZofran4mg(),
                        c.getOtherZofran(),
                        c.getAdditionalOrders(),
                        c.getPatient().getId(),
                        c.getCreatedBy(),
                        c.getLastModifiedBy()
                ))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There is No Data For Returning"
                        )
                );
    }


    public DtoForReturnIdOnly dtoForReturnIdOnly(AnesthesiaPhysicianOrders chart) {
        return Optional.ofNullable(chart)
                .map(
                        c -> new DtoForReturnIdOnly(
                                c.getId()

                        )).orElseThrow(

                        () -> new ResourceNotFoundException(
                                "There is No Data For Returning"
                        )
                );

    }

    @Override
    public BaseChartsDto mapToDto(BaseCharts baseCharts) {
        AnesthesiaPhysicianOrders newChart = (AnesthesiaPhysicianOrders) baseCharts;
        if (newChart == null) {
            return null;
        }
        AnesthesiaPhysicianOrdersDto dto = new AnesthesiaPhysicianOrdersDto();
        dto.setId(newChart.getId());
        dto.setPatientId(newChart.getPatient().getId());
        dto.setDoctorId(newChart.getPatient().getDoctorSpecials().getDoctor().getId());
        dto.setDoctorName(newChart.getPatient().getDoctorSpecials().getDoctor().getUsernameSpecial());
        dto.setPatientName(newChart.getPatient().getPatientData().getFullName());
        dto.setPatientAge(newChart.getPatient().getPatientDate().getAge());
        dto.setPatientGender(newChart.getPatient().getPatientData().getGender().name());
        dto.setWardName(newChart.getPatient().getWard().getName());
        dto.setBedNumber(newChart.getPatient().getBed().getBedNumber());
        dto.setChartName(newChart.getChartName());
        dto.setAnesthesiologistName(newChart.getAnesthesiologistName());
        dto.setOxygen(newChart.getOxygen());
        dto.setIvPerHour(newChart.getIvPerHour());
        dto.setIvRun(newChart.getIvRun());
        dto.setDcIv(newChart.getDcIv());
        dto.setToradol(newChart.getToradol());
        dto.setTradolMg(newChart.getToradolMg());
        dto.setOtherParenteral(newChart.getOtherParenteral());
        dto.setMotrin(newChart.getMotrin());
        dto.setOtherOral(newChart.getOtherOral());
        dto.setZofran4mg(newChart.getZofran4mg());
        dto.setOtherZofran(newChart.getOtherZofran());
        dto.setAdditionalOrders(newChart.getAdditionalOrders());
        dto.setCreatedBy(newChart.getCreatedBy());
        dto.setLastModifiedBy(newChart.getLastModifiedBy());
        return dto;
    }

    @Override
    public Class<AnesthesiaPhysicianOrders> getMappedClass() {
        return AnesthesiaPhysicianOrders.class;
    }
}
