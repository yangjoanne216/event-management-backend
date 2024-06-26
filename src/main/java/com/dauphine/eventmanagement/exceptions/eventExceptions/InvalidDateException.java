package com.dauphine.eventmanagement.exceptions.eventExceptions;

public class InvalidDateException extends RuntimeException {

  public InvalidDateException() {

    super("end time cannot be before start time.");
  }
}
