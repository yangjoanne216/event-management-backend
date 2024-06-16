package com.dauphine.eventmanagement.dto;

import java.util.UUID;

public class FeedbackRequest {

  private UUID id_event;
  private String content;
  private Integer note;

  public UUID getId_event() {
    return id_event;
  }

  public void setId_event(UUID id_event) {
    this.id_event = id_event;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getNote() {
    return note;
  }

  public void setNote(Integer note) {
    this.note = note;
  }
}
