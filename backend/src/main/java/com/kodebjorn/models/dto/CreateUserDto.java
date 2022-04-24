package com.kodebjorn.models.dto;

import com.kodebjorn.models.UserCredential;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Introspected
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {

  public String username;
  public String password;
  public String email;


  public UserCredential getUserCredential() {
    return new UserCredential(username, password, email);
  }

  @Override
  public String toString() {
    return "CreateUserDto{" +
        "username='" + username + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
