package com.kodebjorn.security;

import com.kodebjorn.controllers.ExceptionUtils;
import com.kodebjorn.models.User;
import com.kodebjorn.services.UserService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import jakarta.inject.Singleton;

@Singleton
@Introspected
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthenticationProviderUserPassword(UserService service) {
        userService = service;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {

        return Flux.create(emitter -> {
            User user;
            try {
                user = userService.findByUsername(authenticationRequest.getIdentity().toString());
            } catch (HttpStatusException ex) {
                throw ExceptionUtils.notAuthenticatedException();
            }
        if (passwordEncoder.matches(authenticationRequest.getSecret().toString(), user.getUserCredential().getPassword())) {
            AuthenticationResponse success = AuthenticationResponse.success((String) authenticationRequest.getIdentity());
            emitter.next(success);
            System.out.println(success.isAuthenticated());
            emitter.complete();
        } else {
            emitter.error(AuthenticationResponse.exception());
        }

        }, FluxSink.OverflowStrategy.ERROR);
    }


}
