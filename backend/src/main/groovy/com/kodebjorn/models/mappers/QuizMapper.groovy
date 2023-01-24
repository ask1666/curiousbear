package com.kodebjorn.models.mappers

import com.kodebjorn.models.Quiz
import com.kodebjorn.models.dto.QuizAntity
import com.kodebjorn.models.dto.QuizEntryAntity
import groovy.transform.CompileStatic

@CompileStatic
class QuizMapper {

    private static QuizMapper INSTANCE = new QuizMapper()

    static QuizMapper getInstance() {
        return INSTANCE
    }

    QuizEntryMapper quizEntryMapper = QuizEntryMapper.instance

    @SuppressWarnings('GrMethodMayBeStatic')
    QuizAntity mapToApi(Quiz quiz) {
        return new QuizAntity(
            id: quiz.id,
            title: quiz.title,
            quizEntryAntities: getQuizEntryAntities(quiz),
            description: quiz.description,
            isPublic: quiz.isPublic
        )
    }

    static Quiz mapUpdatedFields(Quiz oldQuiz, Quiz newQuiz) {
        return new Quiz(
            id: newQuiz.id ? newQuiz.id : oldQuiz.id,
            title: newQuiz.title == null ? oldQuiz.title : newQuiz.title,
            quizEntries: oldQuiz.quizEntries,
            description: newQuiz.description == null ? oldQuiz.description : newQuiz.description,
            isPublic: newQuiz.isPublic == null ? oldQuiz.isPublic : newQuiz.isPublic,
        )
    }

    private static List<QuizEntryAntity> getQuizEntryAntities(Quiz quiz) {
        if (quiz.quizEntries) {
            quiz.quizEntries.size()
            return quiz.quizEntries.collect { quizEntryMapper.mapToApi(it) }
        } else {
            return []
        }
    }

}
