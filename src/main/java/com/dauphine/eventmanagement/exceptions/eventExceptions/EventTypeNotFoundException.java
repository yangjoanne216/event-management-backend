package com.dauphine.eventmanagement.exceptions.eventExceptions;

import java.util.UUID;

public class EventTypeNotFoundException extends RuntimeException {

  public EventTypeNotFoundException(UUID id) {
    super("Event type with ID " + id + " not found. Please check the event type ID and try again.");
  }
}