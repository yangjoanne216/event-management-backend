package com.dauphine.eventmanagement.exceptions.participationExceptions;


public class SelfOrganizedEventException extends RuntimeException {

  public SelfOrganizedEventException() {
    super("Cannot participate in an event organized by oneself.");
  }
}
