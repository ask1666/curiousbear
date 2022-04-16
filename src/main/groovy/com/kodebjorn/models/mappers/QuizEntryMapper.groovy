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
}
