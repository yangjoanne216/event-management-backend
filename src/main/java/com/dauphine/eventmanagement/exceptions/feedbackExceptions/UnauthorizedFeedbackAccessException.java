package com.dauphine.eventmanagement.exceptions.feedbackExceptions;

public class UnauthorizedFeedbackAccessException extends RuntimeException {

  public UnauthorizedFeedbackAccessException() {
    super("User cannot give or update feedback to an event they did not participate in.");
  }
}
