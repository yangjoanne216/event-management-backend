package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Feedback;
import com.dauphine.eventmanagement.models.IdFeedback;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, IdFeedback> {

  List<Feedback> findByEvent(Event event);

  //List<Feedback> findAllByUser_IdUser(UUID idUser);

  boolean existsById(IdFeedback idFeedback);

  List<Feedback> findByEvent_IdEvent(UUID idEvent);
}
