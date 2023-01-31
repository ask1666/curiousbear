package com.kodebjorn.repositories

import com.kodebjorn.models.User
import com.kodebjorn.models.UserCredential
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Subject

@MicronautTest
class UserRepositoryTest extends RepositorySpecification {

    @Inject
    @Subject
    UserRepository userRepository

    @Inject
    UserCredentialRepository ucRepository;

    def 'save'() {
        given:
            def uc = new UserCredential('username', 'password', 'email')
            User user = new User(uc)
        when:
            int userId = userRepository.save(user)
        then:
            def foundUser = userRepository.findOne(userId).orElseThrow()
            foundUser.userCredential.id instanceof Integer
            foundUser.userCredential.username == 'username'

    }

}