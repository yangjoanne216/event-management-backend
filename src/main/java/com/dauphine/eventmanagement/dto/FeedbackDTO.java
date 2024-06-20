package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class FeedbackDTO {

  private UUID idEvent;
  private UserDTO userDto;
  private LocalDateTime date;
  private String content;
  private Integer score;


  public FeedbackDTO(UUID idEvent, UserDTO userDto, LocalDateTime date, String content,
      Integer score) {
    this.idEvent = idEvent;
    this.userDto = userDto;
    this.date = date;
    this.content = content;
    this.score = score;
  }

  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
  }

  public UserDTO getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDTO userDto) {
    this.userDto = userDto;
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
