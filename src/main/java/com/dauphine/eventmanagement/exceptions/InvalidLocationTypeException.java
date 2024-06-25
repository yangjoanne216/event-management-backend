package com.dauphine.eventmanagement.exceptions;

public class InvalidLocationTypeException extends RuntimeException {

  public InvalidLocationTypeException() {
    super(
        "Invalid location type provided. We have 3 Types of Location : 'ONLINE' 'ONSITE' and 'HYBRID' ");
  }

}
