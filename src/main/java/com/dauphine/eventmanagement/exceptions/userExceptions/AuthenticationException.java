package com.dauphine.eventmanagement.exceptions.userExceptions;

public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message) {
    super(message);
  }
}
