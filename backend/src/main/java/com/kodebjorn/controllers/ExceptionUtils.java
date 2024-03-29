package com.kodebjorn.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

import java.util.function.Supplier;

public class ExceptionUtils {

    public static Supplier<HttpStatusException> getUserNotFoundException() {
        return () -> new HttpStatusException(HttpStatus.NOT_FOUND, "Could not find user.");
    }
    public static Supplier<HttpStatusException> getUserDuplicateException() {
        return () -> new HttpStatusException(HttpStatus.CONFLICT, "User already exists.");
    }

    public static Supplier<HttpStatusException> getQuizNotFoundException() {
        return () -> new HttpStatusException(HttpStatus.NOT_FOUND, "Could not find quiz.");
    }

    public static Supplier<HttpStatusException> getQuizEntryNotFoundException() {
        return () -> new HttpStatusException(HttpStatus.NOT_FOUND, "Could not find quizEntry.");
    }

    public static HttpStatusException notAuthenticatedException() {
        return new HttpStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }


    public static HttpStatusException createUserException(String message) {
        return new HttpStatusException(HttpStatus.BAD_REQUEST, "Error creating user: " + message);
    }

}
