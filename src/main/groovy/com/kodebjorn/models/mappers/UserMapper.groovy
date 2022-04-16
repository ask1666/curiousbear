package com.kodebjorn.models.mappers

import com.kodebjorn.models.User
import com.kodebjorn.models.dto.UserAntity
import io.micronaut.core.annotation.Introspected

@Introspected
class UserMapper {

   static UserAntity mapToApi(User user) {
       return new UserAntity(
           username: user.userCredential.username,
           email: user.userCredential.email
       )
   }
}
