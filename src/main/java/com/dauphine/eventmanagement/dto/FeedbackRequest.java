package com.dauphine.eventmanagement.dto;

import java.util.UUID;

public class FeedbackRequest {

  private UUID idEvent;
  private String content;
  private Integer score;


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

  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
  }
}
