package com.kodebjorn.services;

import com.kodebjorn.models.QuizEntry;
import com.kodebjorn.repositories.QuizEntryRepository;
import com.kodebjorn.repositories.SuperRepository;
import io.micronaut.core.annotation.Introspected;

import jakarta.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getQuizEntryNotFoundException;

@Singleton
@Introspected
public class QuizEntryService {

    private final QuizEntryRepository quizEntryRepository;
    private final SuperRepository superRepository;

    public QuizEntryService(QuizEntryRepository quizEntryRepository,
                            SuperRepository repository) {
        this.quizEntryRepository = quizEntryRepository;
        superRepository = repository;
    }

    public List<QuizEntry> getAll() {
        return quizEntryRepository.findAll();
    }

    public List<QuizEntry> getByQuizTitle(String quizTitle) {
        return quizEntryRepository.findByQuizTitle(quizTitle);
    }

    @Transactional
    public QuizEntry getById(Integer id) {
        return quizEntryRepository.findById(id).orElseThrow(getQuizEntryNotFoundException());
    }

    public QuizEntry save(QuizEntry quizEntry) {
        return superRepository.save(quizEntry);
    }

    public void deleteById(Integer id) {
        var quizEntry = quizEntryRepository.findById(id).orElseThrow(getQuizEntryNotFoundException());
        superRepository.delete(quizEntry);
    }

}
