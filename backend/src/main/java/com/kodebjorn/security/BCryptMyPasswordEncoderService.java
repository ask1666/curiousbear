package com.kodebjorn.security;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.inject.Singleton;
import javax.validation.constraints.NotBlank;

@Singleton
@Introspected
public class BCryptMyPasswordEncoderService implements MyPasswordEncoder {

    private final PasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override
    public String encode(@NonNull @NotBlank String rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public boolean matches(@NonNull @NotBlank String rawPassword,
                           @NonNull @NotBlank String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}