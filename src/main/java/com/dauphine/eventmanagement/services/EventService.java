package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.dto.SearchCriteria;
import com.dauphine.eventmanagement.exceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.EventTypeNotFoundException;
import com.dauphine.eventmanagement.exceptions.InvalidDateException;
import com.dauphine.eventmanagement.exceptions.LocationNotFoundException;
import com.dauphine.eventmanagement.exceptions.UnauthorizedEventModificationException;
import com.dauphine.eventmanagement.exceptions.UserNotFoundException;
import com.dauphine.eventmanagement.models.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

  List<Event> findAllEvents();

  List<Event> getAllLikeTitle(String title);

  Event findEventById(UUID id_event) throws EventNotFoundException;

  Event createMyEvent(String title, String description, LocalDateTime start_time,
      LocalDateTime end_time, UUID typeEventId, String typeLocation, String image,
      UUID id_city, UUID id_organizer)
      throws InvalidDateException, EventTypeNotFoundException, UserNotFoundException, LocationNotFoundException, EventTimePastException;

  Event updateMyEvent(UUID id_event, String title, String description,
      LocalDateTime start_time, LocalDateTime end_time, UUID typeEventId, String typeLocation,
      String image,
      UUID locationId)
      throws UnauthorizedEventModificationException, EventNotFoundException, InvalidDateException, EventTypeNotFoundException, LocationNotFoundException, EventTimePastException;

  void deleteMyEvent(UUID id_event)
      throws EventNotFoundException, UnauthorizedEventModificationException;

  List<Event> findEventsByTypeId(UUID id_type_event) throws EventTypeNotFoundException;

  List<Event> findEventsByDateRange(LocalDateTime start, LocalDateTime end)
      throws InvalidDateException;

  List<Event> findAllEventsOrderedByScore();

  List<Event> findEventsByLocationId(UUID id_city) throws LocationNotFoundException;


  List<Event> findAllEventsOrderedByStartTime();

  List<Event> findAllMyEventsByUserEmail(String email);

  List<Event> searchEvents(SearchCriteria criteria);

}
