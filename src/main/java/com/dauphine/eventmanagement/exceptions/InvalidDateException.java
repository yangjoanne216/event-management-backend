package com.dauphine.eventmanagement.exceptions;

public class InvalidDateException extends RuntimeException {

  public InvalidDateException() {

    super("end time cannot be before start time.");
  }
}
