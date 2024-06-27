package com.dauphine.eventmanagement.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

  @EmbeddedId
  private IdFeedback idFeedback;

  @ManyToOne
  @MapsId("idEvent") // Maps id Event part of composite ID
  @JoinColumn(name = "id_event", nullable = false)
  private Event event;

  @ManyToOne
  @MapsId("idUser") // Maps id User part of composite ID
  @JoinColumn(name = "id_user", nullable = false)
  private User user;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false)
  private Integer score; // Should validate that score is <= 5

  public IdFeedback getIdFeedback() {
    return idFeedback;
  }

  public void setIdFeedback(IdFeedback id) {
    this.idFeedback = id;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}