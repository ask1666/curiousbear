package com.kodebjorn.security;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;

import javax.validation.constraints.NotBlank;

@Introspected
public interface MyPasswordEncoder {
    String encode(@NotBlank @NonNull String rawPassword);

    boolean matches(@NotBlank @NonNull String rawPassword,
                    @NotBlank @NonNull String encodedPassword);
}
