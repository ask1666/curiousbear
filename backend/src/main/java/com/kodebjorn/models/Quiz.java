package com.kodebjorn.models;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Table(name = "quiz")
@Entity
@Introspected
public class Quiz {

  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  private User user;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "quiz")
  private List<QuizEntry> quizEntries = new ArrayList<>();

  private String title;
  private String description;
  private Boolean isPublic = false;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<QuizEntry> getQuizEntries() {return quizEntries;}

  public void setQuizEntries(List<QuizEntry> quizEntries) {
    if (this.quizEntries != null) {
      this.quizEntries.clear();
      this.quizEntries.addAll(quizEntries);
    }
  }

  public void clearQuizEntries() {
    this.quizEntries.clear();
  }

  public void addQuizEntry(QuizEntry quizEntry) {
    if (this.quizEntries != null && this.quizEntries.size() != 0) {
      this.quizEntries.add(quizEntry);
    } else {
      this.quizEntries = List.of(quizEntry);
    }
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

  public void setIsPublic(Boolean isPublic) {
    this.isPublic = isPublic;
  }

  @Override
  public String toString() {
    return "Quiz{" +
        "id=" + id +
        ", quizEntry=" + quizEntries +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", isPublic=" + isPublic +
        '}';
  }
}