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

@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id_event;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private LocalDateTime start_time;

  @Column(nullable = false)
  private LocalDateTime end_time;

  @ManyToOne
  @JoinColumn(name = "id_type_event", nullable = false)
  private TypeEvent typeEvent;

  //online/onsite/hybride
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TypeLocation typeLocation;

  @ManyToOne
  @JoinColumn(name = "id_location", nullable = false)
  private Location location;

  private String image;

  private double note;

  public UUID getId_event() {
    return id_event;
  }

  public void setId_event(UUID id_event) {
    this.id_event = id_event;
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

  public double getNote() {
    return note;
  }

  public void setNote(double note) {
    this.note = note;
  }
}
