package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackAlreadyExistsException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackAccessException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackDeletionException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import com.dauphine.eventmanagement.models.Feedback;
import java.util.List;
import java.util.UUID;

public interface FeedbackService {

  List<Feedback> getAllFeedbackByEventId(UUID eventId);

  Feedback updateMyFeedback(UUID eventId, UUID userId, String content, Integer score)
      throws UserNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException;

  void deleteMyFeedback(UUID eventId, UUID userId)
      throws UnauthorizedFeedbackDeletionException;

  Feedback createMyFeedback(UUID idUser, UUID idEvent, String content, Integer score)
      throws UserNotFoundException, EventNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException;
}
