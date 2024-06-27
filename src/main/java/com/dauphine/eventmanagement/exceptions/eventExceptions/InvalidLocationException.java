package com.dauphine.eventmanagement.exceptions.eventExceptions;

public class InvalidLocationException extends RuntimeException {

  public InvalidLocationException() {
    super(
        "ONLINE event should not have a location. The event of type HYBRID and ONSITE event should have a location ");
  }

}
