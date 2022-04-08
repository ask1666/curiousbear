package com.kodebjorn.models;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Table(name = "user_credentials", indexes = {
    @Index(name = "user_credentials_email_key", columnList = "email", unique = true),
    @Index(name = "user_credentials_username_key", columnList = "username", unique = true)
})
@Entity
@Introspected
public class UserCredential {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "username", nullable = false, length = 25)
  private String username;

  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}