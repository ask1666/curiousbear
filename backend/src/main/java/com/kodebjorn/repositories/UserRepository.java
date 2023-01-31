package com.kodebjorn.repositories;

import com.kodebjorn.models.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class UserRepository implements GenericRepository<User, Integer> {

    public abstract Optional<User> findOne(Integer id);

    public abstract List<User> findAll();

    public abstract Integer save(User user);

    public abstract void delete(User user);

    public List<User> getAll() {
        return findAll();
    }

    public Optional<User> findById(Integer id) {
        return findOne(id);
    }


}
