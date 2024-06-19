package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Feedback;
import com.dauphine.eventmanagement.models.IdFeedback;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.EventRepository;
import com.dauphine.eventmanagement.repositories.FeedbackRepository;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.FeedbackService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbackRepository feedbackRepository;
  private final UserRepository userRepository;

  private final EventRepository eventRepository;

  public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository,
      EventRepository eventRepository) {
    this.feedbackRepository = feedbackRepository;
    this.userRepository = userRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public Feedback createFeedback(UUID idUser, UUID idEvent, String content, Integer score) {
    User user = userRepository.findById(idUser)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));
    Event event = eventRepository.findById(idEvent)
        .orElseThrow(() -> new RuntimeException("Event not found with ID: " + idEvent));
    Feedback feedback = new Feedback();
    IdFeedback id = new IdFeedback(idEvent, idUser);
    feedback.setIdFeedback(id);
    feedback.setEvent(event);
    feedback.setUser(user);
    feedback.setDate(LocalDateTime.now());
    feedback.setContent(content);
    feedback.setScore(score);

    return feedbackRepository.save(feedback);
  }


  @Override
  public Feedback updateFeedback(UUID idEvent, UUID idUser, String content, Integer score) {
    IdFeedback id = new IdFeedback(idEvent, idUser);
    Feedback feedback = feedbackRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));

    Event event = eventRepository.findById(idEvent)
        .orElseThrow(() -> new RuntimeException("Event not found with ID: " + idEvent));
    User user = userRepository.findById(idUser)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

    feedback.setEvent(event);
    feedback.setUser(user);
    feedback.setContent(content);
    feedback.setScore(score);
    
    return feedbackRepository.save(feedback);
  }

  @Override
  public void deleteFeedback(UUID idEvent, UUID idUser) {
    IdFeedback id = new IdFeedback(idEvent, idUser);
    feedbackRepository.deleteById(id);
  }

  @Override
  public List<Feedback> getAllFeedbackByEventId(UUID eventId) {
    return feedbackRepository.findByEvent_IdEvent(eventId);
  }
}