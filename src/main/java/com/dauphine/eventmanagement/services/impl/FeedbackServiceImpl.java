package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackAlreadyExistsException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackForFutureEventException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackAccessException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackDeletionException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Feedback;
import com.dauphine.eventmanagement.models.IdFeedback;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.EventRepository;
import com.dauphine.eventmanagement.repositories.FeedbackRepository;
import com.dauphine.eventmanagement.repositories.ParticipationRepository;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.FeedbackService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbackRepository feedbackRepository;
  private final ParticipationRepository participationRepository;
  private final UserRepository userRepository;

  private final EventRepository eventRepository;

  public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
      ParticipationRepository participationRepository, UserRepository userRepository,
      EventRepository eventRepository) {
    this.feedbackRepository = feedbackRepository;
    this.participationRepository = participationRepository;
    this.userRepository = userRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public Feedback createMyFeedback(UUID idUser, UUID idEvent, String content, Integer score)
      throws UserNotFoundException, EventNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException {
    //handle exceptions
    User user = userRepository.findById(idUser)
        .orElseThrow(() -> new UserNotFoundException(idUser));

    Event event = eventRepository.findByIdEvent(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
    if (event.getStartTime().isAfter(LocalDateTime.now())) {
      throw new FeedbackForFutureEventException();
    }
    boolean hasParticipated = participationRepository.existsByIdIdEventAndIdIdUser(idEvent, idUser);
    if (!hasParticipated) {
      throw new UnauthorizedFeedbackAccessException();
    }
    IdFeedback id = new IdFeedback(idEvent, idUser);
    boolean feedbackExists = feedbackRepository.existsById(id);
    if (feedbackExists) {
      throw new FeedbackAlreadyExistsException();
    }

    Feedback feedback = new Feedback();
    feedback.setIdFeedback(id);
    feedback.setEvent(event);
    feedback.setUser(user);
    feedback.setDate(LocalDateTime.now());
    feedback.setContent(content);
    feedback.setScore(score);

    return feedbackRepository.save(feedback);
  }


  @Override
  public Feedback updateMyFeedback(UUID idEvent, UUID idUser, String content, Integer score)
      throws UserNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException {
    //handle Exception
    IdFeedback id = new IdFeedback(idEvent, idUser);
    Feedback feedback = feedbackRepository.findById(id)
        .orElseThrow(() -> new UnauthorizedFeedbackAccessException());

    Event event = eventRepository.findByIdEvent(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
    if (event.getStartTime().isAfter(LocalDateTime.now())) {
      throw new FeedbackForFutureEventException();
    }
    User user = userRepository.findById(idUser)
        .orElseThrow(() -> new UserNotFoundException(idUser));

    feedback.setEvent(event);
    feedback.setUser(user);
    feedback.setContent(content);
    feedback.setScore(score);

    return feedbackRepository.save(feedback);
  }

  @Override
  public void deleteMyFeedback(UUID idEvent, UUID idUser)
      throws UnauthorizedFeedbackDeletionException {
    IdFeedback id = new IdFeedback(idEvent, idUser);
    Feedback feedback = feedbackRepository.findById(id)
        .orElseThrow(() -> new EventNotFoundException(idEvent));

    User currentUser = userRepository.findById(idUser)
        .orElseThrow(() -> new RuntimeException("Current user not found"));

    if (!feedback.getUser().getIdUser().equals(currentUser.getIdUser())) {
      throw new UnauthorizedFeedbackDeletionException();
    }

    if (!feedback.getUser().getIdUser().equals(currentUser.getIdUser())) {
      throw new UnauthorizedFeedbackDeletionException();
    }
    feedbackRepository.deleteById(id);
  }

  @Override
  public List<Feedback> getAllFeedbackByEventId(UUID eventId) {
    Event event = eventRepository.findByIdEvent(eventId).orElseThrow(() ->
        new EntityNotFoundException("Unable to find event id " + eventId)
    );
    return feedbackRepository.findByEvent(event);
  }
}