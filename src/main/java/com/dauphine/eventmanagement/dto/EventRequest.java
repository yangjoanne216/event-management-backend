package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;

public class EventRequest {

  private String title;
  private String description;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String typeEventName;
  private String typeLocation;
  private String image;
  private String locationName;


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


  public String getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(String typeLocation) {
    this.typeLocation = typeLocation;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTypeEventName() {
    return typeEventName;
  }

  public void setTypeEventName(String typeEventName) {
    this.typeEventName = typeEventName;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }
}
