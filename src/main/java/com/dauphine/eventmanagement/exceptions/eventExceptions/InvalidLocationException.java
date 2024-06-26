package com.dauphine.eventmanagement.exceptions.eventExceptions;

public class InvalidLocationException extends RuntimeException {

  public InvalidLocationException() {
    super(
        "ONLINE event should not have a location ID. The event of type HYBRID and ONLINE event should have a location ID ");
  }

}
