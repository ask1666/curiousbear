package com.kodebjorn.controllers;

import com.kodebjorn.models.User;
import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.services.UserService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;

@Controller("/api/users")
@Introspected
public class UserController {

  private UserService userService;

  public UserController (UserService userService) {
    this.userService = userService;
  }

  @Get
  public HttpResponse<List<User>> getAll() {
    return HttpResponse.ok(userService.getAllUsers());
  }

  @Post
  public HttpResponse<User> create(@Body CreateUserDto createUserDto) {
    var user = userService
    var userCredential = createUserDto.getUserCredential();
  }

}
