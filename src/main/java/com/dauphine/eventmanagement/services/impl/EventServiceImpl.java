package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.services.EventService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  @Override
  public List<Event> findAllEvents() {
    return null;
  }

  @Override
  public List<Event> getAllLikeTitle(String title) {
    return null;
  }

  @Override
  public List<Event> findPastEvents() {
    return null;
  }

  @Override
  public List<Event> findFutureEvents() {
    return null;
  }

  @Override
  public Event findEventById(UUID id_event) {
    return null;
  }

  @Override
  public Event createEvent(String title, String description, LocalDateTime start_time,
      LocalDateTime end_time, UUID typeEventId, String typeLocation, String image,
      UUID locationId, UUID id_organizer) {
    return null;
  }

  @Override
  public Event updateEvent(UUID id_event, String title, String description,
      LocalDateTime start_time, LocalDateTime end_time, UUID typeEventId, String typeLocation,
      String image, UUID locationId) {
    return null;
  }

  @Override
  public void deleteEvent(UUID id_event) {

  }

  @Override
  public List<Event> findEventsByTypeId(UUID id_type_event) {
    return null;
  }

  @Override
  public List<Event> findEventsByDateRange(LocalDateTime start, LocalDateTime end) {
    return null;
  }
  
  @Override
  public List<Event> findAllEventsOrderedByStartDate() {
    return null;
  }

  @Override
  public List<Event> findAllEventsOrderedByNote() {
    return null;
  }

  @Override
  public List<Event> findEventsByLocationId(UUID id_city) {
    return null;
  }
}
