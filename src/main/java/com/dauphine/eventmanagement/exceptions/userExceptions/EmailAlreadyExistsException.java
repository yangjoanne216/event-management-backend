package com.dauphine.eventmanagement.exceptions.userExceptions;

public class EmailAlreadyExistsException extends RuntimeException {

  public EmailAlreadyExistsException(String email) {
    super("Login already exists for email: " + email);
  }

}
