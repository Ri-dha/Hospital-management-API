package com.azu.hospital.Patients.charts.burnDiagram.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "burn_body_diagram_table")
@Entity
public class BurnBodyDiagram {
  @Id
  @SequenceGenerator(
          name = "burn_body_diagram_sequence",
          sequenceName = "burn_body_diagram_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "burn_body_diagram_sequence"
  )
  private Long id;

  private Float frontHead;

  private Float backHead;

  private Float frontNeck;

  private Float backNeck;

  private Float frontShould;

  private Float backShould;

  private Float leftFrontUpperArm;

  private Float leftBackUpperArm;

  private Float leftFrontForearm;

  private Float leftBackForearm;

  private Float leftFrontHand;

  private Float leftBackHand;

  private Float rightFrontUpperArm;

  private Float rightBackUpperArm;

  private Float rightFrontForearm;

  private Float rightBackForearm;

  private Float rightFrontHand;

  private Float rightBackHand;

  private Float chest;

  private Float abdomen;

  private Float leftShoulderBlade;

  private Float rightShoulderBlade;

  private Float leftBack;

  private Float rightBack;

  private Float leftLumbarRegion;

  private Float rightLumbarRegion;

  private Float rightButtock;

  private Float leftButtock;

  private Float sexualOrgan;

  private Float frontLeftThigh;

  private Float backLeftThigh;

  private Float frontBackThigh;

  private Float backBackThigh;

  private Float frontLeftLeg;

  private Float backLeftLeg;

  private Float frontBackLeg;

  private Float backBackLeg;

  private Float frontLeftFeet;

  private Float backLeftFeet;

  private Float frontBackFeet;

  private Float backBackFeet;

  public BurnBodyDiagram() {
  }

  public BurnBodyDiagram(
          Long id,
          Float frontHead,
          Float backHead,
          Float frontNeck,
          Float backNeck,
          Float frontShould,
          Float backShould,
          Float leftFrontUpperArm,
          Float leftBackUpperArm,
          Float leftFrontForearm,
          Float leftBackForearm,
          Float leftFrontHand,
          Float leftBackHand,
          Float rightFrontUpperArm,
          Float rightBackUpperArm,
          Float rightFrontForearm,
          Float rightBackForearm,
          Float rightFrontHand,
          Float rightBackHand,
          Float chest,
          Float abdomen,
          Float leftShoulderBlade,
          Float rightShoulderBlade,
          Float leftBack,
          Float rightBack,
          Float leftLumbarRegion,
          Float rightLumbarRegion,
          Float rightButtock,
          Float leftButtock,
          Float sexualOrgan,
          Float frontLeftThigh,
          Float backLeftThigh,
          Float frontBackThigh,
          Float backBackThigh,
          Float frontLeftLeg,
          Float backLeftLeg,
          Float frontBackLeg,
          Float backBackLeg,
          Float frontLeftFeet,
          Float backLeftFeet,
          Float frontBackFeet,
          Float backBackFeet
  ) {
    this.id = id;
    this.frontHead = frontHead;
    this.backHead = backHead;
    this.frontNeck = frontNeck;
    this.backNeck = backNeck;
    this.frontShould = frontShould;
    this.backShould = backShould;
    this.leftFrontUpperArm = leftFrontUpperArm;
    this.leftBackUpperArm = leftBackUpperArm;
    this.leftFrontForearm = leftFrontForearm;
    this.leftBackForearm = leftBackForearm;
    this.leftFrontHand = leftFrontHand;
    this.leftBackHand = leftBackHand;
    this.rightFrontUpperArm = rightFrontUpperArm;
    this.rightBackUpperArm = rightBackUpperArm;
    this.rightFrontForearm = rightFrontForearm;
    this.rightBackForearm = rightBackForearm;
    this.rightFrontHand = rightFrontHand;
    this.rightBackHand = rightBackHand;
    this.chest = chest;
    this.abdomen = abdomen;
    this.leftShoulderBlade = leftShoulderBlade;
    this.rightShoulderBlade = rightShoulderBlade;
    this.leftBack = leftBack;
    this.rightBack = rightBack;
    this.leftLumbarRegion = leftLumbarRegion;
    this.rightLumbarRegion = rightLumbarRegion;
    this.rightButtock = rightButtock;
    this.leftButtock = leftButtock;
    this.sexualOrgan = sexualOrgan;
    this.frontLeftThigh = frontLeftThigh;
    this.backLeftThigh = backLeftThigh;
    this.frontBackThigh = frontBackThigh;
    this.backBackThigh = backBackThigh;
    this.frontLeftLeg = frontLeftLeg;
    this.backLeftLeg = backLeftLeg;
    this.frontBackLeg = frontBackLeg;
    this.backBackLeg = backBackLeg;
    this.frontLeftFeet = frontLeftFeet;
    this.backLeftFeet = backLeftFeet;
    this.frontBackFeet = frontBackFeet;
    this.backBackFeet = backBackFeet;
  }
}
