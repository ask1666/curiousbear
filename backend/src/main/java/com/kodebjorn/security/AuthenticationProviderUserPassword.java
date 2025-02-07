package com.kodebjorn.security;

import com.kodebjorn.controllers.ExceptionUtils;
import com.kodebjorn.models.User;
import com.kodebjorn.services.UserService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider;
import io.micronaut.security.authentication.provider.ReactiveAuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton
@Introspected
public class AuthenticationProviderUserPassword implements HttpRequestReactiveAuthenticationProvider<String> {

    private final UserService userService;
    private final MyPasswordEncoder passwordEncoder;

    @Inject
    public AuthenticationProviderUserPassword(UserService service, MyPasswordEncoder encoder) {
        userService = service;
        passwordEncoder = encoder;
    }

    @Override
    public @NonNull Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<String> requestContext, @NonNull AuthenticationRequest<String, String> authenticationRequest) {
        return authenticate(authenticationRequest);
    }

    @Override
    public @NonNull Publisher<AuthenticationResponse> authenticate(@NonNull AuthenticationRequest<String, String> authenticationRequest) {
        return Flux.create(emitter -> {
            User user;
            try {
                String identity = authenticationRequest.getIdentity().toLowerCase();
                user = userService.findByUsername(identity);
            } catch (HttpStatusException ex) {
                throw ExceptionUtils.notAuthenticatedException();
            }
            if (passwordEncoder.matches(authenticationRequest.getSecret(), user.getUserCredential().getPassword())) {
                AuthenticationResponse success = AuthenticationResponse.success(authenticationRequest.getIdentity());
                emitter.next(success);
                System.out.println(success.isAuthenticated());
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }

        }, FluxSink.OverflowStrategy.ERROR);
    }
}
