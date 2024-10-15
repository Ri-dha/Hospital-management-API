package com.azu.hospital.humanResource.UserRating.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "rating_user")
@Getter
@Setter
public class UserRating {

  @Id
  @SequenceGenerator(sequenceName = "rating_user_id_seq" , name = "rating_user_id_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private BaseUser user;

  private Integer rating = 0;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  public UserRating(Integer rating) {
    this.rating=rating;
  }

  public UserRating() {

  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }
  @PrePersist
  public void setCreatedAt() {
    createdAt = Instant.now();
  }

  @PreUpdate
  public void setUpdatedAt(){
    updatedAt = Instant.now();
  }


}
