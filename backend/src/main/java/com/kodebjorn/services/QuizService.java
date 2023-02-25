package com.kodebjorn.services;

import com.kodebjorn.models.Quiz;
import com.kodebjorn.repositories.QuizRepository;
import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;

import static com.kodebjorn.controllers.ExceptionUtils.getQuizNotFoundException;

@Singleton
@Introspected
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz findById(Integer quizId) {
        return quizRepository.findOne(quizId).orElseThrow(getQuizNotFoundException());
    }

    @Transactional
    public Quiz findByTitle(String quizTitle) {
        return quizRepository.findOneByTitle(quizTitle).orElseThrow(getQuizNotFoundException());
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public void deleteByTitle(String title) {
        var quiz = quizRepository.findOneByTitle(title).orElseThrow(getQuizNotFoundException());
        quizRepository.delete(quiz);
    }

}
