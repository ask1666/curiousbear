package com.kodebjorn.models.dto;

import com.kodebjorn.models.Quiz;
import com.kodebjorn.models.QuizEntry;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Introspected
public class CreateQuizDto {

    @NotNull
    private String username;
    @NotNull
    private List<QuizEntry> quizEntry;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Boolean isPublic;

    public Quiz getQuiz() {
        var quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setIsPublic(isPublic);
        return quiz;
    }

    public CreateQuizDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<QuizEntry> getQuizEntry() {
        return quizEntry;
    }

    public void setQuizEntry(List<QuizEntry> quizEntry) {
        this.quizEntry = quizEntry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}

