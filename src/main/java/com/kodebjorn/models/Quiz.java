package com.kodebjorn.models;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;

@Table(name = "quiz")
@Entity
@Introspected
public class Quiz {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_id", nullable = true)
  private User user;

  @Column(name = "title", nullable = false)
  private String title;

  @Lob
  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "is_public")
  private Boolean isPublic;

  public Boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(Boolean isPublic) {
    this.isPublic = isPublic;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
}