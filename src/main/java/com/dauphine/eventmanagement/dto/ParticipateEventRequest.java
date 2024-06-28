package com.dauphine.eventmanagement.dto;

import java.util.UUID;

public class ParticipateEventRequest {

  private UUID idEvent;

  public UUID getIdEvent() {
    return idEvent;
  }

  public void setIdEvent(UUID idEvent) {
    this.idEvent = idEvent;
  }
}
