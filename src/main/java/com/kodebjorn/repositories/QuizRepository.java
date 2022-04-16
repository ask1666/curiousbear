package com.kodebjorn.repositories;

import com.kodebjorn.models.Quiz;
import com.kodebjorn.models.Quiz.QuizFetcher;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public abstract class QuizRepository implements GenericRepository<Quiz, Integer> {

    @PersistenceContext
    private EntityManager em;

    abstract Optional<Quiz> findOneByTitle(String title);

    abstract Optional<Quiz> findOne(Integer id);

    abstract List<Quiz> findAll();

    public List<Quiz> getAll() {
        return findAll();
    }

    @Transactional
    public List<Quiz> getAll(QuizFetcher fetcher) {
        return findAll().stream().map(fetcher::fetch).collect(Collectors.toList());
    }

    public Optional<Quiz> findById(Integer id) {
        return findOne(id);
    }

    public Optional<Quiz> findByTitle(String title, QuizFetcher fetcher) {
        return findOneByTitle(title).map(fetcher::fetch);
    }

    public Optional<Quiz> findByTitle(String title) {
        return findOneByTitle(title);
    }
}
