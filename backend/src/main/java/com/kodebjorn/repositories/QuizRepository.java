package com.kodebjorn.repositories;

import com.kodebjorn.models.Quiz;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class QuizRepository implements GenericRepository<Quiz, Integer> {

    public abstract Integer save(Quiz quiz);

    public abstract void delete(Quiz quiz);

    public abstract Optional<Quiz> findOne(Integer id);

    public abstract Optional<Quiz> findOneByTitle(String title);

    public abstract List<Quiz> findAll();
}
