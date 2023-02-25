package com.kodebjorn.models;


import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Introspected
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserCredential userCredential;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Quiz> quiz;

    //TODO: Store quiz progress

    public List<Quiz> getQuiz() {
        if (quiz == null) {
            return new ArrayList<>();
        }
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public User() {
    }

    public User(UserCredential userCredential) {
        this.userCredential = userCredential;
        this.userCredential.setUser(this);
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCredential=" + userCredential +
                ", quiz=" + quiz +
                '}';
    }
}