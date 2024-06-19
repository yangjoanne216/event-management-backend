package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.User;
import java.util.List;
import java.util.UUID;

public interface ParticipationService {

  void participate(UUID idUser, UUID idEvent);

  void cancelParticipation(UUID idUser, UUID idEvent);

  List<User> getParticipants(UUID id_event);
}
