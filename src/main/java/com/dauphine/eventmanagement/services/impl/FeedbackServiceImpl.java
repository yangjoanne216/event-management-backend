package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Feedback;
import com.dauphine.eventmanagement.services.FeedbackService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  @Override
  public Feedback createFeedback(UUID id_user, UUID id_event, String content, Integer note) {
    return null;
  }

  @Override
  public Feedback modifyFeedback(UUID id_user, UUID id_event, String content, Integer note) {
    return null;
  }

  @Override
  public void deleteFeedback(UUID id_feedback) {

  }

  @Override
  public List<Feedback> getAllFeedbackOfAnEvent(UUID id_event) {
    return null;
  }
}
