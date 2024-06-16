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

@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_event;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private LocalDateTime start;

  @Column(nullable = false)
  private LocalDateTime end;

  @ManyToOne
  @JoinColumn(name = "id_type_event", nullable = false)
  private TypeEvent typeEvent;

  //online/onsite/hybride
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TypeLocation typeLocation;

  @ManyToOne
  @JoinColumn(name = "location", nullable = false)
  private Location location;

  private String image;

  public Long getId_event() {
    return id_event;
  }

  public void setId_event(Long id_event) {
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

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
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

}
