package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.IdParticipation;
import com.dauphine.eventmanagement.models.Participation;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.EventRepository;
import com.dauphine.eventmanagement.repositories.ParticipationRepository;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.ParticipationService;
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
  public void participate(UUID idUser, UUID idEvent) {
    //Todo:Exception
    Participation participation = new Participation();
    Event event = eventRepository.findById(idEvent).orElse(null);
    User user = userRepository.findById(idUser).orElse(null);
    IdParticipation idParticipation = new IdParticipation(idEvent, idUser);
    if (user == null && user == null) {
      throw new IllegalStateException("Event and User not found!");
    }
    if (event == null) {
      throw new IllegalStateException("Event not found!");
    }
    if (user == null) {
      throw new IllegalStateException("User not found!");
    }
    participation.setEvent(event);
    participation.setUser(user);
    participation.setId(idParticipation);
    participationRepository.save(participation);

  }

  @Override
  public void cancelParticipation(UUID idUser, UUID idEvent) {
    //Todo throw exception when id_event et id_user ne trouve pas
    participationRepository.deleteByIdIdEventAndIdIdUser(idEvent, idUser);
  }


  @Override
  public List<User> getParticipants(UUID idEvent) {
    return participationRepository.findParticipantsByEventId(idEvent);
  }
}
