package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EventDTO {

  private UUID idEvent;
  private String title;
  private String description;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String typeEventName; //getTypEvent().getName()
  private String typeLocationName; //getTypeLocation().getName()
  private UserDTO organizer;
  private List<UserDTO> participants;
  private List<FeedbackDTO> feedbacks;


  public EventDTO(UUID idEvent, String title, String description, LocalDateTime startTime,
      LocalDateTime endTime, String typeEventName, String typeLocationName, UserDTO organizer,
      List<UserDTO> participants, List<FeedbackDTO> feedbacks) {
    this.idEvent = idEvent;
    this.title = title;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.typeEventName = typeEventName;
    this.typeLocationName = typeLocationName;
    this.organizer = organizer;
    this.participants = participants;
    this.feedbacks = feedbacks;
  }

  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public String getTypeEventName() {
    return typeEventName;
  }

  public void setTypeEventName(String typeEventName) {
    this.typeEventName = typeEventName;
  }

  public String getTypeLocationName() {
    return typeLocationName;
  }

  public void setTypeLocationName(String typeLocationName) {
    this.typeLocationName = typeLocationName;
  }

  public UserDTO getOrganizer() {
    return organizer;
  }

  public void setOrganizer(UserDTO organizer) {
    this.organizer = organizer;
  }

  public List<UserDTO> getParticipants() {
    return participants;
  }

  public void setParticipants(List<UserDTO> participants) {
    this.participants = participants;
  }

  public List<FeedbackDTO> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(List<FeedbackDTO> feedbacks) {
    this.feedbacks = feedbacks;
  }
}
