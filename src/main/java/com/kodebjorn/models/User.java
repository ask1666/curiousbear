package com.kodebjorn.models;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Table(name = "users")
@Entity
@Introspected
public class User {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_credential_id")
  private UserCredential userCredential;

  public UserCredential getUserCredential() {
    return userCredential;
  }

  public void setUserCredential(UserCredential userCredential) {
    this.userCredential = userCredential;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}