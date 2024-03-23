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
            User saved = userRepository.save(user)
        then:
            saved.userCredential.id instanceof Integer
            saved.userCredential.username == 'username'
    }

    def 'isUnique'() {
        given:
            def uc = new UserCredential('username', 'password', 'email')
            User user = new User(uc)
        expect:
            ucRepository.isUniqueIgnoreCase(
                    uc.username,
                    uc.email
            )
        when:
            userRepository.save(user)
        then:
            !ucRepository.isUniqueIgnoreCase(
                    uc.username,
                    uc.email
            )
    }

    def 'delete'() {
        given:
            def uc = new UserCredential('username', 'password', 'email')
            User user = new User(uc)
        when:
            userRepository.delete(user)
        then:
            userRepository.findAll() == []
    }

}