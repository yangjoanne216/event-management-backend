package com.dauphine.eventmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

//Todo create Dto for event
@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idEvent;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private LocalDateTime startTime;

  @Column(nullable = false)
  private LocalDateTime endTime;

  @ManyToOne
  @JoinColumn(name = "idTypeEvent", nullable = false)
  private TypeEvent typeEvent;

  //online/onsite/hybride
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TypeLocation typeLocation;

  @ManyToOne
  @JoinColumn(name = "idLocation", nullable = false)
  private Location location;

  @ManyToOne
  @JoinColumn(name = "idOrganizer", nullable = false)
  private User organizer;

  private String image;

  private Double score;

  public Event(UUID idEvent) {
    this.idEvent = idEvent;
  }

  public Event() {

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

  public TypeEvent getTypeEvent() {
    return typeEvent;
  }

  public void setTypeEvent(TypeEvent typeEvent) {
    this.typeEvent = typeEvent;
  }

  public TypeLocation getTypeLocation() {
    return typeLocation;
  }

  public void setTypeLocation(TypeLocation typeLocation) {
    this.typeLocation = typeLocation;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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

  public double getScore() {
    return score != null ? score : 0.0;
  }

  public void setScore(double note) {
    this.score = note;
  }

  public User getOrganizer() {
    return organizer;
  }

  public void setOrganizer(User organizer) {
    this.organizer = organizer;
  }
}
