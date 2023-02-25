package com.kodebjorn.repositories;

import com.kodebjorn.models.QuizEntry;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class QuizEntryRepository implements GenericRepository<QuizEntry, Integer> {

    public abstract Optional<QuizEntry> findOneById(Integer id);

    public abstract List<QuizEntry> findAll();

    public abstract List<QuizEntry> findByQuizTitle(String title);

    public abstract QuizEntry save(QuizEntry quizEntry);

    public abstract void delete(QuizEntry quizEntry);
}
