package com.kodebjorn.controllers;

import com.kodebjorn.models.UserCredential;
import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.models.mappers.UserMapper;
import com.kodebjorn.repositories.UserCredentialRepository;
import com.kodebjorn.services.UserService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kodebjorn.models.mappers.UserMapper.mapToApi;

@Controller("/api/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Introspected
public class UserController {

  private final UserService userService;
  private final UserCredentialRepository userCredentialRepository;
  PasswordEncoder encoder = new BCryptPasswordEncoder();

  public UserController(UserService userService,
                        UserCredentialRepository userCredentialRepository) {
    this.userService = userService;
    this.userCredentialRepository = userCredentialRepository;
  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get
  public HttpResponse<?> getAll() {
    Iterable<UserCredential> userIterable = userCredentialRepository.findAll();
    ArrayList<UserCredential> userList = new ArrayList<>();

    userIterable.iterator().forEachRemaining(userList::add);
    System.out.println(userList);
    return HttpResponse.ok(List.of(userService.getAllUsers().stream().map(UserMapper::mapToApi).collect(Collectors.toList()), userList));
  }

  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(consumes = MediaType.APPLICATION_JSON)
  public HttpResponse<?> create(@Valid @Body CreateUserDto createUserDto) {
    createUserDto.password = encoder.encode(createUserDto.password);
    return HttpResponse.ok(
        mapToApi(userService.createUser(createUserDto))
    );
  }

  @Delete("/deleteByUsername/{username}")
  public HttpResponse<?> deleteAll(@PathVariable String username) {
    userService.deleteByUsername(username);
    return HttpResponse.ok("its ok");
  }

  @Delete("/delete/{userId}")
  public HttpResponse<?> deleteById(@PathVariable Integer userId) {
    userService.delete(userId);
    return HttpResponse.ok("its ok");
  }

}
