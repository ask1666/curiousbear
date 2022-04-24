package com.kodebjorn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodebjorn.models.utils.SuperEntity;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Table(name = "user_credentials", indexes = {
    @Index(name = "user_credentials_email_key", columnList = "email", unique = true),
    @Index(name = "user_credentials_username_key", columnList = "username", unique = true)
})
@Entity
@Introspected
public class UserCredential implements SuperEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userCredential")
    @JsonIgnore
    private User user;

    @Size(min = 3, max = 50, message
        = "username must be between 3 and 50 characters")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Size(min = 3, message
        = "password must be larger than 3 characters")
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Size(min = 3, max = 50, message
        = "Email must be between 3 and 50 characters")
    @Email(message = "Email must be valid.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public UserCredential() {

    }

    public UserCredential(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}