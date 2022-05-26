package com.kodebjorn.controllers;

import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.models.mappers.UserMapper;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.kodebjorn.models.mappers.UserMapper.mapToApi;

@Controller("/api/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Introspected
public class UserController {

    private final UserService userService;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get
    public HttpResponse<?> getAllUsers() {
        return HttpResponse.ok(
            List.of(
                userService.getAllUsers().stream()
                    .map(UserMapper::mapToApi)
                    .collect(Collectors.toList())
            )
        );
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> createUser(@Valid @Body CreateUserDto createUserDto) {
        createUserDto.password = encoder.encode(createUserDto.password);
        return HttpResponse.ok(
            mapToApi(userService.createUser(createUserDto))
        );
    }

    @Delete("/deleteByUsername/{username}")
    public HttpResponse<?> deletebyUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return HttpResponse.ok("its ok");
    }

    @Delete("/delete/{userId}")
    public HttpResponse<?> deleteUserById(@PathVariable Integer userId) {
        userService.delete(userId);
        return HttpResponse.ok("its ok");
    }

}
