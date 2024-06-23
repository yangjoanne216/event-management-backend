package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Location;
import com.dauphine.eventmanagement.models.TypeEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, UUID> {

  @Query("""
          SELECT event
          FROM Event event
          WHERE UPPER(event.title) LIKE UPPER(CONCAT('%', :title ,'%'))
      """)
  List<Event> findAllLikeTitleOrderByStartTimeDesc(String title);

  /*@Query("SELECT e FROM Event e WHERE e.endTime < :now")
  List<Event> findPastEvents(LocalDateTime now);

  @Query("SELECT e FROM Event e WHERE e.endTime>:now")
  List<Event> findFutureEvents(LocalDateTime now);*/

  @Query("SELECT e FROM Event e WHERE e.startTime>= :start AND e.endTime <= :end")
  List<Event> findByDateRangeOrderByStartTime(LocalDateTime start, LocalDateTime end);

  @Query("SELECT e FROM Event e ORDER BY COALESCE(e.score, 0) DESC")
  List<Event> findAllByOrderByScore();

  List<Event> findAllByLocationOrderByStartTimeDesc(Location location);

  List<Event> findAllByOrderByStartTime();

  List<Event> findAllByOrderByStartTimeDesc();

  List<Event> findAllByTypeEventOrderByStartTimeDesc(TypeEvent type);

  Optional<Event> findByIdEventOrderByStartTimeDesc(UUID idEvent);

  @Query("SELECT e FROM Event e JOIN e.participants p WHERE p.user.email = :email")
  List<Event> findEventsByParticipantEmail(@Param("email") String email);

  @Query("SELECT e FROM Event e WHERE e.organizer.idUser = :idUser")
  List<Event> findEventsByIdOrganizer(@Param("idUser") UUID idUser);

  Optional<Event> findByIdEvent(UUID eventId);

  List<Event> findAll(Specification<Event> spec, Sort sort);
}
