package com.kodebjorn.models.mappers


import com.kodebjorn.models.QuizEntry
import com.kodebjorn.models.dto.QuizEntryAntity
import io.micronaut.core.annotation.Introspected

@Introspected
class QuizEntryMapper {

    static QuizEntryAntity mapToApi(QuizEntry quizEntry) {
        return new QuizEntryAntity(
            id: quizEntry.id,
            quizEntryType: quizEntry.quizEntryType,
            question: quizEntry.question,
            options: quizEntry.options,
            answer: quizEntry.answer
        )
    }

    static QuizEntry mapUpdatedFields(QuizEntry oldQuizEntry, QuizEntry newQuizEntry) {
        return new QuizEntry(
            id: newQuizEntry.id ? newQuizEntry.id : oldQuizEntry.id,
            quizEntryType: newQuizEntry.quizEntryType == null ? oldQuizEntry.quizEntryType : newQuizEntry.quizEntryType,
            question: oldQuizEntry.question,
            options: newQuizEntry.options == null ? oldQuizEntry.options : newQuizEntry.options,
            answer: newQuizEntry.answer == null ? oldQuizEntry.answer : newQuizEntry.answer,
        )
    }
}
