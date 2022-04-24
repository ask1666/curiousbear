package com.kodebjorn.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class QuizAntity {

    private Integer id;
    private String title;
    private String description;
    @JsonProperty
    private List<QuizEntryAntity> quizEntryAntities;
    private Boolean isPublic;

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

    public List<QuizEntryAntity> getQuizEntryAntities() {
        return quizEntryAntities;
    }

    public void setQuizEntryAntities(List<QuizEntryAntity> quizEntryAntities) {
        this.quizEntryAntities = quizEntryAntities;
    }

    @Override
    public String toString() {
        return "QuizAntity{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", quizEntryAntity=" + quizEntryAntities +
            ", isPublic=" + isPublic +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
