package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LevelOfConsciousness {
  private Boolean awake;
  private Boolean alert;
  private Boolean drowsy;
  private Boolean restless;
  private Boolean confused;
  private Boolean sedated;

  public LevelOfConsciousness() {
  }

  @JsonCreator
  public LevelOfConsciousness(
          @JsonProperty("awake") Boolean awake,
          @JsonProperty("alert") Boolean alert,
          @JsonProperty("drowsy") Boolean drowsy,
          @JsonProperty("restless") Boolean restless,
          @JsonProperty("confused") Boolean confused,
          @JsonProperty("sedated") Boolean sedated
  ) {
    this.awake = awake;
    this.alert = alert;
    this.drowsy = drowsy;
    this.restless = restless;
    this.confused = confused;
    this.sedated = sedated;
  }


}
