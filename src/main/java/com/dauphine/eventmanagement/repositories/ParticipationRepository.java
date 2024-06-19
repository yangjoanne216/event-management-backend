package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.IdParticipation;
import com.dauphine.eventmanagement.models.Participation;
import com.dauphine.eventmanagement.models.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParticipationRepository extends JpaRepository<Participation, IdParticipation> {

  @Query("SELECT p.user FROM Participation p WHERE p.id.idEvent = :idEvent")
  List<User> findParticipantsByEventId(UUID idEvent);

  void deleteByIdIdEventAndIdIdUser(UUID idEvent, UUID idUser);
}
