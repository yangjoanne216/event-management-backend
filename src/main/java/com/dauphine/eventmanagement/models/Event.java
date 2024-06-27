package com.dauphine.eventmanagement.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
  // Participants via Participation
  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private List<Participation> participants;

  // Feedbacks
  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private List<Feedback> feedbacks;

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

  public void setScore(Double score) {
    this.score = score;
  }

  public User getOrganizer() {
    return organizer;
  }

  public void setOrganizer(User organizer) {
    this.organizer = organizer;
  }

  public List<Participation> getParticipants() {
    return participants;
  }

  public void setParticipants(
      List<Participation> participants) {
    this.participants = participants;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }
}
