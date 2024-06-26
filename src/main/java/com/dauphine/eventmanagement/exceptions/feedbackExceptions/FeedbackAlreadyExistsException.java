package com.dauphine.eventmanagement.exceptions.feedbackExceptions;

public class FeedbackAlreadyExistsException extends RuntimeException {

  public FeedbackAlreadyExistsException() {
    super("User has already submitted feedback for this event.");
  }
}

