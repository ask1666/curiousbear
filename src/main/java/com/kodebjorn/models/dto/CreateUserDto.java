package com.kodebjorn.models.dto;

import com.kodebjorn.models.User;
import com.kodebjorn.models.UserCredential;

public class CreateUserDto {

  private String username;
  private String password;
  private String email;


  public UserCredential getUserCredential() {
    return UserCredential.builder()
        .email(email)
        .username(username)
        .password(password)
        .build();
  }

  public User getUser() {
    return User.builder()
        .userCredential(getUserCredential())
        .build();
  }

}
