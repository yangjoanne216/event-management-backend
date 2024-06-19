package com.dauphine.eventmanagement.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class IdFeedback implements Serializable {

  private UUID idEvent;
  private UUID idUser;

  public IdFeedback() {
  }

  public IdFeedback(UUID idEvent, UUID idUser) {
    this.idEvent = idEvent;
    this.idUser = idUser;
  }
  

  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
  }

  public UUID getIdUser() {
    return idUser;
  }

  public void setIdUser(UUID idUser) {
    this.idUser = idUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdFeedback that = (IdFeedback) o;
    return Objects.equals(idEvent, that.idEvent) &&
        Objects.equals(idUser, that.idUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEvent, idUser);
  }
}

