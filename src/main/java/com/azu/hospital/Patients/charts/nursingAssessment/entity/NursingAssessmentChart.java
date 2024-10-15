package com.azu.hospital.Patients.charts.nursingAssessment.entity;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.physical_assment.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "nursing_assessment_chart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class NursingAssessmentChart extends BaseCharts {

    private String chartName = "Nursing Assessment Chart";

    @Embedded
    private LevelOfConsciousness levelOfConsciousness;

    @Embedded
    private Orientation orientation;

    @Embedded
    private MentalState mentalState;

    @Embedded
    private Eyes eyes;

    @Embedded
    private Ears ears;

    @Embedded
    private Mouth mouth;

    @Embedded
    private Nose nose;

    @Embedded
    private Hair hair;

    @Embedded
    private Neck neck;

    @Embedded
    private Skin skin;

    @Embedded
    private Chest chest;

    @Embedded
    private Abdomen abdomen;

    @Embedded
    private UpperExtremities upperExtremities;

    @Embedded
    private LowerExtremities lowerExtremities;

    private String note;

    private BigDecimal painLevel;

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


    public NursingAssessmentChart() {
    }


    public NursingAssessmentChart(Builder builder) {
        this.levelOfConsciousness = builder.chart.levelOfConsciousness;
        this.orientation = builder.chart.orientation;
        this.mentalState = builder.chart.mentalState;
        this.eyes = builder.chart.eyes;
        this.ears = builder.chart.ears;
        this.mouth = builder.chart.mouth;
        this.nose = builder.chart.nose;
        this.hair = builder.chart.hair;
        this.neck = builder.chart.neck;
        this.skin = builder.chart.skin;
        this.chest = builder.chart.chest;
        this.abdomen = builder.chart.abdomen;
        this.upperExtremities = builder.chart.upperExtremities;
        this.lowerExtremities = builder.chart.lowerExtremities;
        this.note = builder.chart.note;
        this.painLevel = builder.chart.painLevel;
    }


    public static class Builder{

        private final NursingAssessmentChart chart = new NursingAssessmentChart();

        public Builder withLevelOfConsciousness(LevelOfConsciousness levelOfConsciousness) {
            chart.levelOfConsciousness = levelOfConsciousness;
            return this;
        }

        public Builder withOrientation(Orientation orientation) {
            chart.orientation = orientation;
            return this;
        }

        public Builder withMentalState(MentalState mentalState){
            chart.mentalState = mentalState;
            return this;
        }

        public Builder withEyes(Eyes eyes){
            chart.eyes = eyes;
            return this;
        }

        public Builder withEars(Ears ears){
            chart.ears = ears;
            return this;
        }

        public Builder withMouth(Mouth mouth){
            chart.mouth = mouth;
            return this;
        }
        public Builder withNose(Nose nose){
            chart.nose = nose;
            return this;
        }
        public Builder withHair(Hair hair){
            chart.hair = hair;
            return this;
        }

        public Builder withNeck(Neck neck){
            chart.neck = neck;
            return this;
        }
        public Builder withSkin(Skin skin){
            chart.skin = skin;
            return this;
        }

        public Builder withChest(Chest chest){
            chart.chest = chest;
            return this;
        }
        public Builder withAbdomen(Abdomen abdomen){
            chart.abdomen = abdomen;
            return this;
        }

        public Builder withUpperExtremities(UpperExtremities upperExtremities){
            chart.upperExtremities = upperExtremities;
            return this;
        }
        public Builder withLowerExtremities(LowerExtremities lowerExtremities){
            chart.lowerExtremities = lowerExtremities;
            return this;
        }
        public Builder withNote(String note){
            chart.note = note;
            return this;
        }
        public Builder withPainLevel(BigDecimal painLevel){
            chart.painLevel = painLevel;
            return this;
        }
        public NursingAssessmentChart build(){
            return new NursingAssessmentChart(this);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NursingAssessmentChart that)) return false;
        return
                 Objects.equals(levelOfConsciousness, that.levelOfConsciousness)
                && Objects.equals(orientation, that.orientation)
                && Objects.equals(mentalState, that.mentalState)
                && Objects.equals(eyes, that.eyes)
                && Objects.equals(ears, that.ears)
                && Objects.equals(mouth, that.mouth)
                && Objects.equals(nose, that.nose)
                && Objects.equals(hair, that.hair)
                && Objects.equals(neck, that.neck)
                && Objects.equals(skin, that.skin)
                && Objects.equals(chest, that.chest)
                && Objects.equals(abdomen, that.abdomen)
                && Objects.equals(upperExtremities, that.upperExtremities)
                && Objects.equals(lowerExtremities, that.lowerExtremities);

    }

    @Override
    public int hashCode() {
        return Objects.hash(levelOfConsciousness, orientation,
                mentalState, eyes, ears, mouth, nose, hair, neck, skin, chest, abdomen,
                upperExtremities, lowerExtremities, note
        );
    }
}
