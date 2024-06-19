package com.dauphine.eventmanagement.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "participation")
public class Participation {

  @EmbeddedId
  private IdParticipation id; //Composite Primary Key

  @ManyToOne
  @MapsId("idEvent") // Maps idEvent part of composite ID
  @JoinColumn(name = "id_event")
  private Event event;

  @ManyToOne
  @MapsId("idUser") // Maps idUser part of composite ID
  @JoinColumn(name = "id_user")
  private User user;

  public IdParticipation getId() {
    return id;
  }

  public void setId(IdParticipation id) {
    this.id = id;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
