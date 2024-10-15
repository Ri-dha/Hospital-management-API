package com.azu.hospital.all_user_sevices.roles_sevices;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Table(name = "roles")

public class Role {
  @Id
  @SequenceGenerator(
          name = "role_id_seq",
          sequenceName = "role_id_seq",
          allocationSize = 55
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer roleId;


  @Column(length = 80)
  private String name;

  @ManyToMany
  private Set<BaseUser> users;


  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<BaseUser> getUsers() {
    return users;
  }

  public void setUsers(Set<BaseUser> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return name;
  }
}