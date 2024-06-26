package com.dauphine.eventmanagement.exceptions.userExceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String email) {
    super("User with email " + email + " not found. Please check your credentials and try again.");
  }

  public UserNotFoundException(UUID id) {
    super("User with id " + id + " not found");
  }
}
