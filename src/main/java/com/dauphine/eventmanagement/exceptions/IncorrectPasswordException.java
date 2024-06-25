package com.dauphine.eventmanagement.exceptions;

public class IncorrectPasswordException extends RuntimeException {

  public IncorrectPasswordException() {
    super("Error in entering old password");
  }

}
