package com.kodebjorn.models;


import com.kodebjorn.models.utils.Fetcher;
import com.kodebjorn.models.utils.WithChildrenEntity;
import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Table(name = "users")
@Entity
@Introspected
public class User extends WithChildrenEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
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

    public void addAllChildren() {
        getQuiz().forEach(this::addChild);
        addChild(getUserCredential());
    }

    public enum UserFetcher implements Fetcher<User> {
        FetchAll(user -> {
           user.getQuiz().size();
           user.getUserCredential().getId();
        }),
        FetchQuiz(user -> user.getQuiz().size()),
        FetchUserCredential(user -> user.getUserCredential().getId());

        private final Consumer<User> consumer;

        UserFetcher(Consumer<User> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void doFetch(User user) {
            consumer.accept(user);
        }
    }
}