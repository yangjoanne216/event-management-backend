package com.dauphine.eventmanagement.exceptions.participationExceptions;

public class NotParticipantException extends RuntimeException {

  public NotParticipantException() {
    super("User is not a participant of this event.");
  }
}
