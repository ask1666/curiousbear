package com.kodebjorn.repositories

import com.kodebjorn.models.Quiz
import com.kodebjorn.models.User
import com.kodebjorn.models.UserCredential
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Subject

import javax.persistence.PersistenceException

@MicronautTest
class QuizRepositoryTest extends RepositorySpecification {

    @Inject
    @Subject
    QuizRepository quizRepository

    @Inject
    UserRepository userRepository

    def 'save without user'() {
        given:
            Quiz quiz = new Quiz(
                    title: 'halloi'
            )
        when:
            quizRepository.save(quiz)
        then:
            thrown(PersistenceException)
    }

    def 'save'() {
        given:
            UserCredential uc = new UserCredential('username', 'password', 'email')
            User user = userRepository.save(new User(uc))

            Quiz quiz = new Quiz(
                    user: user,
                    title: 'halloi'
            )
        when:
            def saved = quizRepository.save(quiz)
        then:
            saved
    }

    def 'delete'() {
        given:
            UserCredential uc = new UserCredential('username', 'password', 'email')
            User user = userRepository.save(new User(uc))
        and:
            Quiz quiz = new Quiz(
                    user: user,
                    title: 'halloi'
            )
        and:
            def saved = quizRepository.findOne(quizRepository.save(quiz).getId()).orElseThrow()
        when:
            quizRepository.delete(saved)
        then: 'Its deleted successfully'
            true
    }

}
