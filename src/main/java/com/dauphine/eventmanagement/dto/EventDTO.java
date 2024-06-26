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

  private String locationName;
  private UserDTO organizer;
  private List<UserDTO> participants;
  private List<FeedbackDTO> feedbacks;

  private Double score;

  private String image;


  public EventDTO(UUID idEvent, String title, String description, LocalDateTime startTime,
      LocalDateTime endTime, String typeEventName, String typeLocationName, String locationName,
      UserDTO organizer,
      List<UserDTO> participants, List<FeedbackDTO> feedbacks, Double score, String image) {
    this.idEvent = idEvent;
    this.title = title;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.typeEventName = typeEventName;
    this.typeLocationName = typeLocationName;
    this.locationName = locationName;
    this.organizer = organizer;
    this.participants = participants;
    this.feedbacks = feedbacks;
    this.score = score;
    this.image = image;
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

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
