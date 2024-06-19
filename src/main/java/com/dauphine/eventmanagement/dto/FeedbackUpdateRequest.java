package com.dauphine.eventmanagement.dto;

public class FeedbackUpdateRequest {

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

}
