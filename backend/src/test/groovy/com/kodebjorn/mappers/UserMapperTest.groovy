package com.kodebjorn.mappers

import com.kodebjorn.Application
import com.kodebjorn.models.User
import com.kodebjorn.models.UserCredential
import com.kodebjorn.models.mappers.UserMapper
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class UserMapperTest extends Specification {

    @Subject
    UserMapper userMapper = UserMapper.instance

    def 'correctly map from User to userAntity'() {
        given:
            User user = new User(new UserCredential('username', 'password', 'email'))
        when:
            def mapped = userMapper.mapToApi(user)
        then:
            mapped
            mapped.username == 'username'
            mapped.email == 'email'

    }
}
