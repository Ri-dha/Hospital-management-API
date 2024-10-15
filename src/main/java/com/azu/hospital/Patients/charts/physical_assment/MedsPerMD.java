package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.MDOrders;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Map;

@Data
public class MedsPerMD {

  @ElementCollection
  private Map<MDOrders, Boolean> marcaine_25;

  @ElementCollection
  private Map<MDOrders, Boolean> marcaine_5;

  @ElementCollection
  private Map<MDOrders, Boolean> marcaine_75;

  @ElementCollection
  private Map<MDOrders, Boolean> lidocaine_1;

  @ElementCollection
  private Map<MDOrders, Boolean> lidocaine_2;

  public MedsPerMD() {
  }

  public MedsPerMD(
          Map<MDOrders, Boolean> marcaine_25,
          Map<MDOrders, Boolean> marcaine_5,
          Map<MDOrders, Boolean> marcaine_75,
          Map<MDOrders, Boolean> lidocaine_1,
          Map<MDOrders, Boolean> lidocaine_2
  ) {
    this.marcaine_25 = marcaine_25;
    this.marcaine_5 = marcaine_5;
    this.marcaine_75 = marcaine_75;
    this.lidocaine_1 = lidocaine_1;
    this.lidocaine_2 = lidocaine_2;
  }
}
