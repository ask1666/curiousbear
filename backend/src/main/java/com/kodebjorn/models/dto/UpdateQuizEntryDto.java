package com.kodebjorn.models.dto;

import com.kodebjorn.models.QuizEntry;
import io.micronaut.core.annotation.Introspected;

import java.util.ArrayList;

@Introspected
public class UpdateQuizEntryDto {

    private String quizEntryType;
    private String question;
    private ArrayList<String> options;
    private String answer;

    public QuizEntry getQuizEntry() {
        var quizEntry = new QuizEntry();
        quizEntry.setQuestion(question);
        quizEntry.setOptions(options);
        quizEntry.setAnswer(answer);
        quizEntry.setQuizEntryType(quizEntryType);
        return quizEntry;
    }

    public UpdateQuizEntryDto() {
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

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
