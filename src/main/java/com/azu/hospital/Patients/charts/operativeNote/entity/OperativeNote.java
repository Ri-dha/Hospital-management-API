package com.azu.hospital.Patients.charts.operativeNote.entity;


import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "operative_note")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OperativeNote extends BaseCharts {

    @Column(columnDefinition = "TEXT")
    private String operativeNote;

    private String operationName;


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

    public OperativeNote() {
    }


    public OperativeNote(Builder builder) {
        this.operativeNote = builder.chart.operativeNote;
        this.operationName = builder.chart.operationName;
    }

    public static class Builder {
        private final OperativeNote chart = new OperativeNote();

        public Builder withOperativeNote (String operativeNote) {
            chart.operativeNote = operativeNote;
            return this;
        }
        public Builder withOperationName (String operationName) {
            chart.operationName = operationName;
            return this;
        }
        public OperativeNote build() {
            return new OperativeNote(this);
        }
    }

}
