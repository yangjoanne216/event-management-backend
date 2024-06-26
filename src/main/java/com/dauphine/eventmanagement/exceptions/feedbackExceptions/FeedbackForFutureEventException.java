package com.dauphine.eventmanagement.exceptions.feedbackExceptions;

public class FeedbackForFutureEventException extends RuntimeException {

  public FeedbackForFutureEventException() {
    super("Cannot provide or update a feedback for an event that has not yet occurred.");
  }
}
