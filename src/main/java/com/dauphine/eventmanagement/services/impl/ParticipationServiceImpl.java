package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.services.ParticipationService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ParticipationServiceImpl implements ParticipationService {

  @Override
  public void participate(UUID id_user, UUID id_event) {

  }

  @Override
  public void cancelParticipation(UUID id_user, UUID id_event) {

  }

  @Override
  public List<User> getParticipants(UUID id_event) {
    return null;
  }
}
