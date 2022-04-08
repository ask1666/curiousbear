package com.kodebjorn.repositories;

import com.kodebjorn.models.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
