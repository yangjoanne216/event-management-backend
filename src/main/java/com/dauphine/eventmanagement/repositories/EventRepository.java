package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Location;
import com.dauphine.eventmanagement.models.TypeEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, UUID> {

  @Query("""
          SELECT event
          FROM Event event
          WHERE UPPER(event.title) LIKE UPPER(CONCAT('%', :title ,'%'))
      """)
  List<Event> findAllLikeTitle(String title);

  @Query("SELECT e FROM Event e WHERE e.endTime < :now")
  List<Event> findPastEvents(LocalDateTime now);

  @Query("SELECT e FROM Event e WHERE e.endTime>:now")
  List<Event> findFutureEvents(LocalDateTime now);

  @Query("SELECT e FROM Event e WHERE e.startTime>= :start AND e.endTime <= :end")
  List<Event> findByDateRange(LocalDateTime start, LocalDateTime end);

  @Query("SELECT e FROM Event e ORDER BY COALESCE(e.score, 0) DESC")
  List<Event> findAllByOrderByScoreDesc();

  List<Event> findAllByLocation(Location location);

  List<Event> findAllByOrderByStartTime();

  List<Event> findAllByTypeEvent(TypeEvent type);

}
