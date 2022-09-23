package com.kodebjorn.models.dto;

import com.kodebjorn.models.QuizEntry;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
public class AddQuizEntryDto {

    @NotNull
    private String quizTitle;
    @NotNull
    private String quizEntryType;
    @NotNull
    private String question;
    @NotNull
    private String[] options;
    @NotNull
    private String answer;

    public QuizEntry getQuizEntry() {
        var quizEntry = new QuizEntry();
        quizEntry.setQuestion(question);
        quizEntry.setOptions(options);
        quizEntry.setAnswer(answer);
        quizEntry.setQuizEntryType(quizEntryType);
        return quizEntry;
    }

    public AddQuizEntryDto() {
    }

    public String getQuizEntryType() {
        return quizEntryType;
    }

    public void setQuizEntryType(String quizEntryType) {
        this.quizEntryType = quizEntryType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
}
