package com.kodebjorn.services;

import com.kodebjorn.models.QuizEntry;
import com.kodebjorn.repositories.QuizEntryRepository;
import io.micronaut.core.annotation.Introspected;

import jakarta.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getQuizEntryNotFoundException;

@Singleton
@Introspected
public class QuizEntryService {

    private final QuizEntryRepository quizEntryRepository;

    public QuizEntryService(QuizEntryRepository quizEntryRepository) {
        this.quizEntryRepository = quizEntryRepository;
    }

    public List<QuizEntry> getAll() {
        return quizEntryRepository.findAll();
    }

    public List<QuizEntry> getByQuizTitle(String quizTitle) {
        return quizEntryRepository.findByQuizTitle(quizTitle);
    }

    @Transactional
    public QuizEntry getById(Integer id) {
        return quizEntryRepository.findOneById(id).orElseThrow(getQuizEntryNotFoundException());
    }

    public QuizEntry save(QuizEntry quizEntry) {
        var createdId = quizEntryRepository.save(quizEntry);
        return quizEntryRepository.findOneById(createdId).orElseThrow();
    }

    public void deleteById(Integer id) {
        var quizEntry = quizEntryRepository.findOneById(id).orElseThrow(getQuizEntryNotFoundException());
        quizEntryRepository.delete(quizEntry);
    }

}
