package com.dauphine.eventmanagement.exceptions;

import java.util.UUID;

public class EventNotFoundException extends RuntimeException {

  public EventNotFoundException(UUID id) {
    super("Event with ID " + id + " not found. Please verify the event ID and try again.");
  }
}