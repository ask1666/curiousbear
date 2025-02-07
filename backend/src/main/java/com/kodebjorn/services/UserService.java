package com.kodebjorn.services;

import com.kodebjorn.controllers.ExceptionUtils;
import com.kodebjorn.models.Quiz;
import com.kodebjorn.models.User;
import com.kodebjorn.models.UserCredential;
import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.repositories.UserCredentialRepository;
import com.kodebjorn.repositories.UserRepository;
import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import javax.validation.Valid;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getUserNotFoundException;


@Singleton
@Introspected
public class UserService {

    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;

    public UserService(UserRepository userRepository,
                       UserCredentialRepository userCredentialRepository) {
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsername(String username) {
        var userCredential = userCredentialRepository.findByUsernameIgnoreCase(username).orElseThrow(getUserNotFoundException());
        return userCredential.getUser();
    }

    @Transactional
    public List<Quiz> getMyQuiz(String username) {
        return findByUsername(username)
                .getQuiz();
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(getUserNotFoundException());
    }

    public User createUser(@Valid CreateUserDto userDto) {
        UserCredential userCredential = userDto.getUserCredential();
        boolean isUnique = userCredentialRepository.isUniqueIgnoreCase(
                userCredential.getUsername(),
                userCredential.getEmail()
        );
        if (!isUnique) {
            throw ExceptionUtils.createUserException("User with that username or email already exists.");
        }
        var user = new User(userCredential);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByUsername(String username) {
        var userCredential = userCredentialRepository.findByUsernameIgnoreCase(username).orElseThrow(getUserNotFoundException());
        var user = userCredential.getUser();
        userRepository.delete(user);
    }

    @Transactional
    public void delete(Integer userId) {
        var user = userRepository.findById(userId).orElseThrow(getUserNotFoundException());
        userRepository.delete(user);
    }

}
