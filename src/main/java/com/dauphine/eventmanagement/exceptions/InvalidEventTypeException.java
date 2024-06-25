package com.dauphine.eventmanagement.exceptions;

public class InvalidEventTypeException extends RuntimeException {

  public InvalidEventTypeException() {
    super("Invalid event type provided. Please verify the event type and try again.");
  }
}
