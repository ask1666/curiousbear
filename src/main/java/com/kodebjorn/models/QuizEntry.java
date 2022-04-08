package com.kodebjorn.models;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.List;

@Table(name = "quiz_entries")
@Entity
@Introspected
public class QuizEntry {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "quiz_id", nullable = false)
  private Quiz quiz;

  @Column(name = "name")
  private String name;

  @Column(name = "quiz_entry_type", nullable = false)
  private String quizEntryType;

  @Lob
  @Column(name = "question", nullable = false)
  private String question;

  @Column(name = "options", nullable = false)
  private String[] options;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}