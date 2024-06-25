package com.dauphine.eventmanagement.exceptions;

public class UnauthorizedEventModificationException extends RuntimeException {

  //"You are not authorized to modify this event. Only the event organizer can make changes."
  // "Cannot delete past events."
  public UnauthorizedEventModificationException(String message) {
    super(message);

  }
}
