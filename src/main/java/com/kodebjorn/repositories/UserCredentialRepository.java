package com.kodebjorn.repositories;

import com.kodebjorn.models.UserCredential;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserCredentialRepository extends CrudRepository<UserCredential, Integer> {
}
