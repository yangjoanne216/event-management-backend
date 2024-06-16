package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventRequest {

  private String title;
  private String description;
  private LocalDateTime start_time;
  private LocalDateTime end_time;
  private UUID typeEventId;
  private String typeLocation;
  private String image;
  private UUID locationId;

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

  public LocalDateTime getStart_time() {
    return start_time;
  }

  public void setStart_time(LocalDateTime start_time) {
    this.start_time = start_time;
  }

  public LocalDateTime getEnd_time() {
    return end_time;
  }

  public void setEnd_time(LocalDateTime end_time) {
    this.end_time = end_time;
  }

  public UUID getTypeEventId() {
    return typeEventId;
  }

  public void setTypeEventId(UUID typeEventId) {
    this.typeEventId = typeEventId;
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

  public UUID getLocationId() {
    return locationId;
  }

  public void setLocationId(UUID locationId) {
    this.locationId = locationId;
  }
}
