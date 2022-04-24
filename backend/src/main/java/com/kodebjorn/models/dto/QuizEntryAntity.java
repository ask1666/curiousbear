package com.kodebjorn.models.dto;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class QuizEntryAntity {

    private String id;
    private String quizTitle;
    private String quizEntryType;
    private String question;
    private List<String> options;
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
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

    @Override
    public String toString() {
        return "QuizEntryAntity{" +
            "quizTitle='" + quizTitle + '\'' +
            ", quizEntryType='" + quizEntryType + '\'' +
            ", question='" + question + '\'' +
            ", options=" + options +
            ", answer='" + answer + '\'' +
            '}';
    }
}
