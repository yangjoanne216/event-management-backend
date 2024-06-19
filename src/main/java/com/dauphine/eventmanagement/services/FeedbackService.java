package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.Feedback;
import java.util.List;
import java.util.UUID;

public interface FeedbackService {

  Feedback updateFeedback(UUID eventId, UUID userId, String content, Integer score);

  void deleteFeedback(UUID eventId, UUID userId);

  List<Feedback> getAllFeedbackByEventId(UUID eventId);

  Feedback createFeedback(UUID idUser, UUID idEvent, String content, Integer score);
}
