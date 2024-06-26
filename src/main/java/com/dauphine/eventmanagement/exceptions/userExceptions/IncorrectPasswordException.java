package com.dauphine.eventmanagement.exceptions.userExceptions;

public class IncorrectPasswordException extends RuntimeException {

  public IncorrectPasswordException() {
    super("Error in entering old password");
  }

}
