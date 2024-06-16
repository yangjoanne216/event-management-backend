package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.User;
import java.util.List;
import java.util.UUID;

public interface ParticipationService {

  void participate(UUID id_user, UUID id_event);

  void cancelParticipation(UUID id_user, UUID id_event);

  List<User> getParticipants(UUID id_event);
}
