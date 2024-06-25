package com.dauphine.eventmanagement.exceptions;

public class EventTimePastException extends RuntimeException {

  public EventTimePastException() {
    super(
        "The event start time has already passed. You cannot modify or delete or create  past events.");
  }
}
