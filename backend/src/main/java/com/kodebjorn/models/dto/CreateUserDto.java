package com.kodebjorn.models.dto;

import com.kodebjorn.models.UserCredential;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Introspected
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {

  @NotNull
  public String username;
  @NotNull
  public String password;
  @NotNull
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
