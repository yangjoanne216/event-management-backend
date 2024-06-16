package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.Feedback;
import java.util.List;
import java.util.UUID;

public interface FeedbackService {

  Feedback createFeedback(UUID id_user, UUID id_event, String content, Integer note);

  Feedback modifyFeedback(UUID id_user, UUID id_event, String content, Integer note);

  void deleteFeedback(UUID id_feedback);

  List<Feedback> getAllFeedbackOfAnEvent(UUID id_event);
}
