package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class FeedbackDTO {

  private UUID idEvent;
  private UserDTO participant;
  private LocalDateTime date;
  private String content;
  private Integer score;

  public FeedbackDTO(UUID idEvent, UserDTO participant, LocalDateTime date, String content,
      Integer score) {
    this.idEvent = idEvent;
    this.participant = participant;
    this.date = date;
    this.content = content;
    this.score = score;
  }

  public UserDTO getParticipant() {
    return participant;
  }

  public void setParticipant(UserDTO participant) {
    this.participant = participant;
  }


  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
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
