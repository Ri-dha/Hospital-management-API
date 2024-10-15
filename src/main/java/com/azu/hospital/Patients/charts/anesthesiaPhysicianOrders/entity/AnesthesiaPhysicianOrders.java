package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assessment_enm.OxygenOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "anesthesia_physician_orders_chart")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class AnesthesiaPhysicianOrders extends BaseCharts {

  private String chartName = "Anesthesia Physician Orders";

  private String AnesthesiologistName;

  private OxygenOrder oxygen;

  private Integer ivPerHour;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean ivRun;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean dcIv;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean toradol;

  private Integer toradolMg;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean otherParenteral;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean motrin;

  private String otherOral;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean zofran4mg ;

  @Column(columnDefinition = "BOOLEAN DEFAULT false")
  private Boolean otherZofran;

  @Column(columnDefinition = "TEXT")
  private String additionalOrders;



  @CreatedBy
  @Column(

          updatable = false
  )
  private Long createdBy;
  @LastModifiedBy
  @Column(
          insertable = false
  )
  private Long LastModifiedBy;


  public AnesthesiaPhysicianOrders() {
  }

  public AnesthesiaPhysicianOrders(Builder builder) {
    this.AnesthesiologistName=builder.chart.AnesthesiologistName;
    this.oxygen = builder.chart.oxygen;
    this.ivPerHour = builder.chart.ivPerHour;
    this.ivRun=builder.chart.ivRun;
    this.dcIv = builder.chart.dcIv;
    this.toradol = builder.chart.toradol;
    this.toradolMg=builder.chart.toradolMg;
    this.otherParenteral = builder.chart.otherParenteral;
    this.motrin = builder.chart.motrin;
    this.otherOral = builder.chart.otherOral;
    this.zofran4mg = builder.chart.zofran4mg;
    this.otherZofran=builder.chart.otherZofran;
    this.additionalOrders = builder.chart.additionalOrders;
  }

  public static class Builder {
    private final AnesthesiaPhysicianOrders chart = new AnesthesiaPhysicianOrders();


    public Builder withAnesthesiologistName(String anesthesiologistName) {
      chart.AnesthesiologistName = anesthesiologistName;
      return this;
    }
    public Builder withOxygen(OxygenOrder oxygen) {
      chart.oxygen = oxygen;
      return this;
    }

    public Builder withIvPerHour(Integer ivPerHour) {
      chart.ivPerHour = ivPerHour;
      return this;
    }

    public Builder withIvRun(Boolean ivRun) {
      chart.ivRun = ivRun;
      return this;
    }

    public Builder withDcIv(Boolean dcIv) {
      chart.dcIv = dcIv;
      return this;
    }

    public Builder withToradol(Boolean toradol) {
      chart.toradol = toradol;
      return this;
    }

    public Builder withToradolMg(Integer toradolMg) {
      chart.toradolMg = toradolMg;
      return this;
    }

    public Builder withOtherParenteral(Boolean otherParenteral) {
      chart.otherParenteral = otherParenteral;
      return this;
    }

    public Builder withMotrin(Boolean motrin) {
      chart.motrin = motrin;
      return this;
    }

    public Builder withOtherOral(String otherOral) {
      chart.otherOral = otherOral;
      return this;
    }

    public Builder withZofran4mg(Boolean zofran4mg) {
      chart.zofran4mg = zofran4mg;
      return this;
    }

    public Builder withOtherZofran(Boolean otherZofran) {
      chart.otherZofran = otherZofran;
      return this;
    }

    public Builder withAdditionalOrders(String additionalOrders) {
      chart.additionalOrders = additionalOrders;
      return this;
    }


    public AnesthesiaPhysicianOrders build() {
      return new AnesthesiaPhysicianOrders(this);
    }
  }



}
