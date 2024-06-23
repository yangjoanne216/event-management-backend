package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.models.Location;
import com.dauphine.eventmanagement.models.TypeEvent;
import com.dauphine.eventmanagement.models.TypeLocation;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.EventRepository;
import com.dauphine.eventmanagement.repositories.LocationRepository;
import com.dauphine.eventmanagement.repositories.ParticipationRepository;
import com.dauphine.eventmanagement.repositories.TypeEventRepository;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.EventService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final TypeEventRepository typeEventRepository;

  private final LocationRepository locationRepository;

  private final UserRepository userRepository;

  private final ParticipationRepository participationRepository;

  public EventServiceImpl(EventRepository eventRepository,
      TypeEventRepository typeEventRepository, LocationRepository locationRepository,
      UserRepository userRepository, ParticipationRepository participationRepository) {
    this.eventRepository = eventRepository;
    this.typeEventRepository = typeEventRepository;
    this.locationRepository = locationRepository;
    this.userRepository = userRepository;
    this.participationRepository = participationRepository;
  }

  @Override
  public List<Event> findAllEvents() {
    return eventRepository.findAllByOrderByStartTimeDesc();
  }

  @Override
  public List<Event> getAllLikeTitle(String title) {
    return eventRepository.findAllLikeTitleOrderByStartTimeDesc(title);
  }

  @Override
  public Event findEventById(UUID idEvent) {
    return eventRepository.findByIdEventOrderByStartTimeDesc(idEvent).orElse(null);
  }

  @Override
  public Event createEvent(String title, String description, LocalDateTime startTime,
      LocalDateTime endTime, UUID idTypeEvent, String typeLocation, String image,
      UUID idLocation, UUID idOrganizer) {
    Event event = new Event();
    event.setTitle(title);
    event.setDescription(description);
    event.setStartTime(startTime);
    event.setEndTime(endTime);
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent).orElse(null);
    event.setTypeEvent(typeEvent);

    event.setTypeLocation(TypeLocation.valueOf(typeLocation));
    event.setImage(image);

    Location location = locationRepository.findById(idLocation).orElse(null);
    event.setLocation(location);

    User user = userRepository.findById(idOrganizer).orElse(null);
    event.setOrganizer(user);
    return eventRepository.save(event);
  }

  @Override
  public Event updateEvent(UUID idEvent, String title, String description,
      LocalDateTime startTime, LocalDateTime endTime, UUID idTypeEvent, String typeLocation,
      String image, UUID idLocation) {
    Event event = eventRepository.findById(idEvent).orElse(null);
    if (event != null) {
      event.setTitle(title);
      event.setDescription(description);
      event.setStartTime(startTime);
      event.setEndTime(endTime);
      TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent).orElse(null);
      event.setTypeEvent(typeEvent);
      TypeLocation typeLocation2 = TypeLocation.valueOf(typeLocation);
      event.setTypeLocation(typeLocation2);
      event.setImage(image);

      Location location = locationRepository.findById(idLocation).orElse(null);
      event.setLocation(location);

      return eventRepository.save(event);
    }
    return null;
  }

  @Override
  public void deleteEvent(UUID idEvent) {
    eventRepository.deleteById(idEvent);
  }

  @Override
  public List<Event> findEventsByTypeId(UUID idTypeEvent) {
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent).orElse(null);
    return eventRepository.findAllByTypeEventOrderByStartTimeDesc(typeEvent);
  }

  @Override
  public List<Event> findEventsByType(UUID idTypeEvent) {
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent).orElse(null);
    return eventRepository.findAllByTypeEventOrderByStartTimeDesc(typeEvent);
  }

  @Override
  public List<Event> findEventsByDateRange(LocalDateTime start, LocalDateTime end) {
    return eventRepository.findByDateRangeOrderByStartTime(start, end);
  }

  @Override
  public List<Event> findEventsByIdUser(UUID idUser) {
    return participationRepository.findEventsByIdIdUser(idUser);
  }

  @Override
  public List<Event> findAllEventsOrderedByStartTime() {
    return eventRepository.findAllByOrderByStartTime();
  }

  @Override
  public List<Event> findAllMyEventsByUserEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    List<Event> organizedEvents = eventRepository.findEventsByIdOrganizer(user.getIdUser());
    List<Event> participatedEvents = participationRepository.findEventsByIdIdUser(user.getIdUser());
    return Stream.concat(organizedEvents.stream(), participatedEvents.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  @Override
  public List<Event> findAllEventsOrderedByStartTimeDesc() {
    return eventRepository.findAllByOrderByStartTimeDesc();
  }

  @Override
  public List<Event> findAllEventsOrderedByScore() {
    return eventRepository.findAllByOrderByScore();
  }

  @Override
  public List<Event> findEventsByLocationId(UUID idCity) {
    Location location = locationRepository.findById(idCity).orElse(null);
    return eventRepository.findAllByLocationOrderByStartTimeDesc(location);
  }
}
