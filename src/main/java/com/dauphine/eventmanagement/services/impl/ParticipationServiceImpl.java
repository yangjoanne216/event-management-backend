package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.NotParticipantException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.SelfOrganizedEventException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.IdParticipation;
import com.dauphine.eventmanagement.models.Participation;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.EventRepository;
import com.dauphine.eventmanagement.repositories.ParticipationRepository;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.ParticipationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ParticipationServiceImpl implements ParticipationService {

  private final ParticipationRepository participationRepository;
  private final EventRepository eventRepository;
  private final UserRepository userRepository;

  public ParticipationServiceImpl(ParticipationRepository participationRepository,
      EventRepository eventRepository, UserRepository userRepository) {
    this.participationRepository = participationRepository;
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void participate(UUID idUser, UUID idEvent)
      throws UserNotFoundException, EventNotFoundException, EventTimePastException, SelfOrganizedEventException {

    //when we can't find user
    User user = userRepository.findById(idUser)
        .orElseThrow(() -> new UserNotFoundException(idUser));
    //When we can't find evnet
    Event event = eventRepository.findByIdEvent(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
    //when event in the past Time
    if (event.getEndTime().isBefore(LocalDateTime.now())) {
      throw new EventTimePastException();
    }
    //when user is a organizer of an event
    if (event.getOrganizer().getIdUser().equals(idUser)) {
      throw new SelfOrganizedEventException();
    }

    IdParticipation idParticipation = new IdParticipation(idEvent, idUser);

    Participation participation = new Participation();
    participation.setEvent(event);
    participation.setUser(user);
    participation.setId(idParticipation);
    participationRepository.save(participation);
    participationRepository.flush();

  }

  @Override
  public void cancelParticipation(UUID idUser, UUID idEvent)
      throws NotParticipantException, EventTimePastException {

    Participation participation = participationRepository.findById(
            new IdParticipation(idEvent, idUser))
        .orElseThrow(() -> new NotParticipantException());

    if (participation.getEvent().getEndTime().isBefore(LocalDateTime.now())) {
      throw new EventTimePastException();
    }
    participationRepository.delete(participation);
  }


  @Override
  public List<User> getParticipants(UUID idEvent) throws EventNotFoundException {
    if (!eventRepository.existsById(idEvent)) {
      throw new EventNotFoundException(idEvent);
    }
    return participationRepository.findParticipantsByEventId(idEvent);
  }
}
