package com.dauphine.eventmanagement.exceptions.feedbackExceptions;

public class UnauthorizedFeedbackDeletionException extends RuntimeException {

  public UnauthorizedFeedbackDeletionException() {
    super("User can only delete their own feedback.");
  }
}
