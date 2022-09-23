package com.kodebjorn.models.mappers

import com.kodebjorn.models.User
import com.kodebjorn.models.dto.UserAntity
import groovy.transform.CompileStatic

@CompileStatic
class UserMapper {

    private static UserMapper INSTANCE = new UserMapper()

    static UserMapper getInstance() {
        return INSTANCE
    }

   UserAntity mapToApi(User user) {
       return new UserAntity(
           username: user.userCredential.username,
           email: user.userCredential.email
       )
   }
}
