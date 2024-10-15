package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customs_salary")
@Getter
@Setter
public class CustomSalary {
  @Id
  @SequenceGenerator(
          sequenceName = "custom_salary_id_seq" ,
          name = "custom_salary_id_seq",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "custom_salary_id_seq"
  )
  private Long id;

  @Column(name = "title")
  @NotNull(message = "title should not be null")
  private String title;

  @NotNull(message = "amount cannot be null")
  @Column(name= "amount")
  private Double amount;
  @NotNull(message = "isDown cannot be null")

//  private Double resetAmount;

  @Column(name = "isDown")
  @ColumnDefault("false")
  private Boolean isDown;

  @Column(name = "is_deleted")
  private Boolean isDeleted = false;

  @Column(columnDefinition = "TEXT")
  private String note;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "base_user_id")
  private BaseUser baseUser;

  @Column(updatable = false)
  @CurrentTimestamp
  private Instant createdAt;

  @Column(updatable = false)
  @UpdateTimestamp
  private Instant updatedAt;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
          name = "main_salary_custom_salary",
          joinColumns = @JoinColumn(name = "custom_salary_id"),
          inverseJoinColumns = @JoinColumn(name = "main_salary_id")
  )
  private List<MainSalaryTable> mainSalaryTableList;


  public CustomSalary() {
  }

  public CustomSalary(String title, Double amount, Boolean down, String note) {
    this.title = title;
    this.amount = amount;
    this.isDown = down;
    this.note = note;
  }


  public CustomSalary(String title, Double amount, Boolean down, String note, boolean b) {
    this.title = title;
    this.amount = amount;
    this.isDown = down;
    this.note = note;
    this.isDeleted = b;
  }
}
