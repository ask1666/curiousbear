package com.kodebjorn.repositories;

import com.kodebjorn.models.User;
import com.kodebjorn.models.User.UserFetcher;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public abstract class UserRepository implements GenericRepository<User, Integer> {

    abstract Optional<User> findOne(Integer id);

    abstract List<User> findAll();

    public List<User> getAll() {
        return findAll();
    }

    public List<User> getAll(UserFetcher fetcher) {
        return findAll().stream().map(fetcher::fetch).collect(Collectors.toList());
    }

    public Optional<User> findById(Integer id) {
        return findOne(id);
    }

    public Optional<User> findById(Integer id, UserFetcher fetcher) {
        return findOne(id).map(fetcher::fetch);
    }

}
