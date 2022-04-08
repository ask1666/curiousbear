package com.kodebjorn.services;

import com.kodebjorn.models.User;
import com.kodebjorn.repositories.UserCredentialRepository;
import com.kodebjorn.repositories.UserRepository;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

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

  public List<User> getAllUsers() {

    Iterable<User> userIterable = userRepository.findAll();
    ArrayList<User> userList = new ArrayList<>();

    userIterable.iterator().forEachRemaining(userList::add);

    return userList;
  }

}
