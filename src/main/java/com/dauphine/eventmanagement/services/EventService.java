package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

  public List<Event> findAllEvents();

  List<Event> getAllLikeTitle(String title);

  public List<Event> findPastEvents();

  public List<Event> findFutureEvents();

  public Event findEventById(UUID id_event);

  public Event createEvent(String title, String description, LocalDateTime start_time,
      LocalDateTime end_time, UUID typeEventId, String typeLocation, String image,
      UUID id_city, UUID id_organizer);

  public Event updateEvent(UUID id_event, String title, String description,
      LocalDateTime start_time, LocalDateTime end_time, UUID typeEventId, String typeLocation,
      String image,
      UUID locationId);

  public void deleteEvent(UUID id_event);

  public List<Event> findEventsByTypeId(UUID id_type_event);

  public List<Event> findEventsByDateRange(LocalDateTime start, LocalDateTime end);

  public List<Event> findAllEventsOrderedByScore();

  public List<Event> findEventsByLocationId(UUID id_city);

  List<Event> findEventsByType(UUID id_type_event);

  List<Event> findAllEventsOrderedByStartTime();

  List<Event> findEventsByIdUser(UUID idUser);
}
