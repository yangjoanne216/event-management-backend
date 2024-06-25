package com.dauphine.eventmanagement.exceptions;

import java.util.UUID;

public class LocationNotFoundException extends RuntimeException {

  public LocationNotFoundException(UUID id) {
    super("Location with ID " + id + " not found. Please check the location ID and try again.");
  }

}
