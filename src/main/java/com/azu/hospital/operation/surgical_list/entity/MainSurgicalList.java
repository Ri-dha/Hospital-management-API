package com.azu.hospital.operation.surgical_list.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "main_surgical_List")
@Getter
@Setter
public class MainSurgicalList {
    @Id
    @SequenceGenerator(
            name = "main_surgical_id_seq",
            sequenceName = "main_surgical_id_seq",
            allocationSize = 30
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "main_surgical_id_seq"
    )

    private Long id;

    @NotBlank(message = "name required")
    @NotNull(message = "name required")
    @NotEmpty(message = "name required")
    @Column(columnDefinition = "TEXT")
    private String surgicalName;

    private String surgicalCode;

    private BigDecimal price = BigDecimal.ZERO;

    private Double hospitalPercentage = 0.0;

    @Column(columnDefinition = "TEXT")
    private String note;


    public MainSurgicalList() {
    }

    public MainSurgicalList(String surgicalName) {
        this.surgicalName = surgicalName;
    }
}
