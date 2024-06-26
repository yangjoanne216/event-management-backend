package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.exceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.UserNotFoundException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.NotParticipantException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.SelfOrganizedEventException;
import com.dauphine.eventmanagement.models.User;
import java.util.List;
import java.util.UUID;

public interface ParticipationService {

  void participate(UUID idUser, UUID idEvent)
      throws UserNotFoundException, EventNotFoundException, EventTimePastException, SelfOrganizedEventException;

  void cancelParticipation(UUID idUser, UUID idEvent)
      throws NotParticipantException, EventTimePastException;

  List<User> getParticipants(UUID id_event) throws EventNotFoundException;
}
