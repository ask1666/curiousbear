package com.kodebjorn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodebjorn.models.utils.SuperEntity;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Introspected
public class QuizEntry implements SuperEntity<Integer> {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  private Quiz quiz;

  @NotNull
  @Column(name = "quiz_entry_type", nullable = false)
  private String quizEntryType;

  @NotNull
  @Lob
  @Column(name = "question", nullable = false, length = 300)
  private String question;

  @NotNull
  @Column(name = "options", nullable = false)
  private String[] options;

  @NotNull
  @Column(name = "answer", nullable = false)
  private String answer;

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String[] getOptions() {
    return options;
  }

  public void setOptions(String[] options) {
    this.options = options;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getQuizEntryType() {
    return quizEntryType;
  }

  public void setQuizEntryType(String quizEntryType) {
    this.quizEntryType = quizEntryType;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "QuizEntry{" +
        "id=" + id +
        ", quiz=" + quiz +
        ", quizEntryType='" + quizEntryType + '\'' +
        ", question='" + question + '\'' +
        ", options=" + Arrays.toString(options) +
        ", answer='" + answer + '\'' +
        '}';
  }
}