package com.kodebjorn.services;

import com.kodebjorn.controllers.ExceptionUtils;
import com.kodebjorn.models.Quiz;
import com.kodebjorn.models.User;
import com.kodebjorn.models.User.UserFetcher;
import com.kodebjorn.models.dto.CreateUserDto;
import com.kodebjorn.repositories.SuperRepository;
import com.kodebjorn.repositories.UserCredentialRepository;
import com.kodebjorn.repositories.UserRepository;
import io.micronaut.core.annotation.Introspected;

import jakarta.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getUserNotFoundException;
import static com.kodebjorn.models.Quiz.QuizFetcher.FetchQuizEntries;
import static com.kodebjorn.models.User.UserFetcher.FetchQuiz;


@Singleton
@Introspected
public class UserService {

    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final SuperRepository superRepository;

    public UserService(UserRepository userRepository,
                       UserCredentialRepository userCredentialRepository,
                       SuperRepository repository) {
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
        superRepository = repository;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAll(UserFetcher.FetchAll);
    }

    @Transactional
    public User findByUsername(String username) {
        var userCredential = userCredentialRepository.findByUsername(username).orElseThrow(getUserNotFoundException());
        return userCredential.getUser();
    }

    @Transactional
    public List<Quiz> getMyQuiz(String username) {
        return FetchQuiz.fetch(findByUsername(username))
            .getQuiz()
            .stream()
            .map(FetchQuizEntries::fetch)
            .toList();
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(getUserNotFoundException());
    }

    public User createUser(@Valid CreateUserDto userDto) {
        var user = new User(userDto.getUserCredential());
        boolean isUnique = userCredentialRepository.isUnique(userDto.getUserCredential().getUsername(), userDto.getUserCredential().getEmail());
        if (!isUnique) {
            throw ExceptionUtils.createUserException("User with that username or email already exists.");
        }
            return superRepository.save(user);
    }

    @Transactional
    public void deleteByUsername(String username) {
        var userCredential = userCredentialRepository.findByUsername(username).orElseThrow(getUserNotFoundException());
        var user = userCredential.getUser();
        superRepository.delete(user);
    }

    @Transactional
    public void delete(Integer userId) {
        var user = userRepository.findById(userId).orElseThrow(getUserNotFoundException());
        superRepository.delete(user);
    }

}
