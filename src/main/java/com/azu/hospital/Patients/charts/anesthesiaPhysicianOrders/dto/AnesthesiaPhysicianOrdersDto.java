package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.OxygenOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class AnesthesiaPhysicianOrdersDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Anesthesia Physician Orders";
    private String anesthesiologistName;

    private String title = "POST OPERATIVE ANESTHESIA PHYSICIAN ORDERS";
    private String link = "physician-orders";

    private OxygenOrder oxygen;

    private Integer ivPerHour;
    private Boolean ivRun;

    private Boolean dcIv;

    private Boolean toradol;
    private Integer tradolMg;

    private Boolean otherParenteral;

    private Boolean motrin;

    private String otherOral;

    private Boolean zofran4mg;
    private Boolean otherZofran;

    private String additionalOrders;

    private Long createdBy;
    private Long LastModifiedBy;


    public AnesthesiaPhysicianOrdersDto() {
    }

    public AnesthesiaPhysicianOrdersDto(
            Long id,
            String chartName,
            String anesthesiologistName,
            OxygenOrder oxygen,
            Integer ivPerHour,
            Boolean ivRun,
            Boolean dcIv,
            Boolean toradol,
            Integer tradolMg,
            Boolean otherParenteral,
            Boolean motrin,
            String otherOral,
            Boolean zofran4mg,
            Boolean otherZofran,
            String additionalOrders,
            Long patientId
    ) {
        this.id = id;
        this.chartName = chartName;
        this.anesthesiologistName = anesthesiologistName;
        this.oxygen = oxygen;
        this.ivPerHour = ivPerHour;
        this.ivRun = ivRun;
        this.dcIv = dcIv;
        this.toradol = toradol;
        this.tradolMg = tradolMg;
        this.otherParenteral = otherParenteral;
        this.motrin = motrin;
        this.otherOral = otherOral;
        this.zofran4mg = zofran4mg;
        this.otherZofran = otherZofran;
        this.additionalOrders = additionalOrders;
        this.patientId = patientId;
    }


    public AnesthesiaPhysicianOrdersDto(
            Long id,
            String chartName,
            String anesthesiologistName,
            OxygenOrder oxygen,
            Integer ivPerHour,
            Boolean ivRun,
            Boolean dcIv,
            Boolean toradol,
            Integer tradolMg,
            Boolean otherParenteral,
            Boolean motrin,
            String otherOral,
            Boolean zofran4mg,
            Boolean otherZofran,
            String additionalOrders,
            Long patientId,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.anesthesiologistName = anesthesiologistName;
        this.oxygen = oxygen;
        this.ivPerHour = ivPerHour;
        this.ivRun = ivRun;
        this.dcIv = dcIv;
        this.toradol = toradol;
        this.tradolMg = tradolMg;
        this.otherParenteral = otherParenteral;
        this.motrin = motrin;
        this.otherOral = otherOral;
        this.zofran4mg = zofran4mg;
        this.otherZofran = otherZofran;
        this.additionalOrders = additionalOrders;
        this.patientId = patientId;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }

}
