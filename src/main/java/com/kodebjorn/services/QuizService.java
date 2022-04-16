package com.kodebjorn.services;

import com.kodebjorn.models.Quiz;
import com.kodebjorn.repositories.SuperRepository;
import com.kodebjorn.repositories.QuizRepository;
import io.micronaut.core.annotation.Introspected;

import jakarta.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getQuizNotFoundException;
import static com.kodebjorn.models.Quiz.QuizFetcher.FetchQuizEntries;

@Singleton
@Introspected
public class QuizService {

    private final QuizRepository quizRepository;
    private final SuperRepository superRepository;

    public QuizService(QuizRepository quizRepository,
                       SuperRepository superRepository) {
        this.quizRepository = quizRepository;
        this.superRepository = superRepository;
    }

    public List<Quiz> getAll() {
        return quizRepository.getAll(FetchQuizEntries);
    }

    public Quiz findById(Integer quizId) {
        return quizRepository.findById(quizId).orElseThrow(getQuizNotFoundException());
    }

    @Transactional
    public Quiz findByTitle(String quizTitle) {
        return quizRepository.findByTitle(quizTitle, FetchQuizEntries).orElseThrow(getQuizNotFoundException());
    }

    public Quiz save(Quiz quiz) {
        return superRepository.save(quiz);
    }

    @Transactional
    public void deleteByTitle(String title) {
        var quiz = quizRepository.findByTitle(title, FetchQuizEntries).orElseThrow(getQuizNotFoundException());
        superRepository.delete(quiz);
    }

}
