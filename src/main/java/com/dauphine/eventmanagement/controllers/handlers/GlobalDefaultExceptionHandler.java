package com.dauphine.eventmanagement.controllers.handlers;


import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTypeNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidDateException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidEventTypeException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidLocationException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidLocationTypeException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.LocationNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.UnauthorizedEventModificationException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackAlreadyExistsException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackForFutureEventException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackAccessException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackDeletionException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.NotParticipantException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.SelfOrganizedEventException;
import com.dauphine.eventmanagement.exceptions.userExceptions.IncorrectPasswordException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex,
      WebRequest request) {
    logger.error("User not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler({
      EventTypeNotFoundException.class,
      LocationNotFoundException.class,
      EventNotFoundException.class

  })
  public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
    logger.error("Resource not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler({
      InvalidDateException.class,
      InvalidEventTypeException.class,
      InvalidLocationTypeException.class,
      FeedbackForFutureEventException.class,
      NotParticipantException.class,
      SelfOrganizedEventException.class,
      InvalidLocationException.class
  })
  public ResponseEntity<String> handleBadRequestException(RuntimeException ex, WebRequest request) {
    logger.error("Bad request: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler({
      UnauthorizedEventModificationException.class,
      IncorrectPasswordException.class,
      UnauthorizedFeedbackAccessException.class,
      FeedbackAlreadyExistsException.class,
      UnauthorizedFeedbackDeletionException.class
  })
  public ResponseEntity<String> handleUnauthorizedException(
      UnauthorizedEventModificationException ex, WebRequest request) {
    logger.error("Unauthorized: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
  }

  @ExceptionHandler(EventTimePastException.class)
  public ResponseEntity<String> handleForbiddenException(EventTimePastException ex,
      WebRequest request) {
    logger.error("Operation forbidden on past event: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
    logger.error("An error occurred: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error occurred: " + ex.getMessage());
  }
}
