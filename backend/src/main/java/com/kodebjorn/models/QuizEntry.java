package com.kodebjorn.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Introspected
public class QuizEntry {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Quiz quiz;

  private String quizEntryType;
  private String question;
  @Type(type = "jsonb")
  private ArrayList<String> options;
  private String answer;

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public ArrayList<String> getOptions() {
    return options;
  }

  public void setOptions(ArrayList<String> options) {
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
        ", options=" + options +
        ", answer='" + answer + '\'' +
        '}';
  }
}