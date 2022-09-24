package com.kodebjorn.controllers;

import com.kodebjorn.models.User;
import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.models.dto.UserAntity;
import com.kodebjorn.models.mappers.UserMapper;
import com.kodebjorn.security.MyPasswordEncoder;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/api/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Introspected
public class UserController {

    UserMapper umMapper = UserMapper.getInstance();

    private final UserService userService;
    private final MyPasswordEncoder encoder;

    public UserController(UserService userService, MyPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get
    public HttpResponse<?> getAllUsers() {
        return HttpResponse.ok(
            List.of(
                userService.getAllUsers().stream()
                    .map(umMapper::mapToApi)
                    .collect(Collectors.toList())
            )
        );
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<UserAntity> createUser(@Valid @Body CreateUserDto createUserDto) {
        createUserDto.password = encoder.encode(createUserDto.password);

        User createdUser = userService.createUser(createUserDto);

        return HttpResponse.ok(umMapper.mapToApi(createdUser));
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
