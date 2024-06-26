package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.dto.SearchCriteria;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTypeNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidDateException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.LocationNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.UnauthorizedEventModificationException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
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
import com.dauphine.eventmanagement.services.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final TypeEventRepository typeEventRepository;

  private final LocationRepository locationRepository;

  private final UserRepository userRepository;

  private final ParticipationRepository participationRepository;

  private final UserService userService;

  public EventServiceImpl(EventRepository eventRepository,
      TypeEventRepository typeEventRepository, LocationRepository locationRepository,
      UserRepository userRepository, ParticipationRepository participationRepository,
      UserService userService) {
    this.eventRepository = eventRepository;
    this.typeEventRepository = typeEventRepository;
    this.locationRepository = locationRepository;
    this.userRepository = userRepository;
    this.participationRepository = participationRepository;
    this.userService = userService;
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
  public Event findEventById(UUID idEvent) throws EventNotFoundException {
    return eventRepository.findByIdEventOrderByStartTimeDesc(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
  }

  @Override
  public Event createMyEvent(String title, String description, LocalDateTime startTime,
      LocalDateTime endTime, UUID idTypeEvent, String typeLocation, String image,
      UUID idLocation, UUID idOrganizer)
      throws InvalidDateException, EventTypeNotFoundException, UserNotFoundException, LocationNotFoundException, EventTimePastException {
    if (startTime.isBefore(LocalDateTime.now())) {
      throw new EventTimePastException();
    }
    if (endTime.isBefore(startTime)) {
      throw new InvalidDateException();
    }
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent)
        .orElseThrow(() -> new EventTypeNotFoundException(idTypeEvent));
    Location location = locationRepository.findById(idLocation)
        .orElseThrow(() -> new LocationNotFoundException(idLocation));
    User user = userRepository.findById(idOrganizer)
        .orElseThrow(() -> new UserNotFoundException(idOrganizer));

    Event event = new Event();
    event.setTitle(title);
    event.setDescription(description);
    event.setStartTime(startTime);
    event.setEndTime(endTime);
    event.setTypeEvent(typeEvent);
    event.setTypeLocation(TypeLocation.valueOf(typeLocation));
    event.setImage(image);
    event.setLocation(location);
    event.setOrganizer(user);
    return eventRepository.save(event);
  }

  @Override
  public Event updateMyEvent(UUID idEvent, String title, String description,
      LocalDateTime startTime, LocalDateTime endTime, UUID idTypeEvent, String typeLocation,
      String image, UUID idLocation)
      throws UnauthorizedEventModificationException, EventNotFoundException, InvalidDateException, EventTypeNotFoundException, LocationNotFoundException, EventTimePastException {
    //Event not found Exception
    Event event = eventRepository.findById(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
    //get current user information
    String email = userService.getCurrentUserEmail();

    // check current user is organizer or not
    if (!event.getOrganizer().getEmail().equals(email)) {
      throw new UnauthorizedEventModificationException(
          "You are not authorized to modify this event. Only the event organizer can make changes.");
    }
    if (startTime.isBefore(LocalDateTime.now())) {
      throw new EventTimePastException();
    }
    if (endTime.isBefore(startTime)) {
      throw new InvalidDateException();
    }
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent)
        .orElseThrow(() -> new EventTypeNotFoundException(idTypeEvent));
    Location location = locationRepository.findById(idLocation)
        .orElseThrow(() -> new LocationNotFoundException(idLocation));

    event.setTitle(title);
    event.setDescription(description);
    event.setStartTime(startTime);
    event.setEndTime(endTime);
    event.setTypeEvent(typeEvent);
    TypeLocation typeLocation2 = TypeLocation.valueOf(typeLocation);
    event.setTypeLocation(typeLocation2);
    event.setImage(image);
    event.setLocation(location);

    return eventRepository.save(event);


  }

  @Override
  public void deleteMyEvent(UUID idEvent)
      throws EventNotFoundException, UnauthorizedEventModificationException {
    // check event is null or not
    Event event = eventRepository.findById(idEvent)
        .orElseThrow(() -> new EventNotFoundException(idEvent));
    // check event is in the pas or not
    if (event.getStartTime().isBefore(LocalDateTime.now())) {
      throw new UnauthorizedEventModificationException("Cannot delete past events.");
    }
    //get current user information
    String email = userService.getCurrentUserEmail();
    // check current user is organizer or not
    if (!event.getOrganizer().getEmail().equals(email)) {
      throw new UnauthorizedEventModificationException(
          "You are not authorized to modify this event. Only the event organizer can make changes.");
    }

    eventRepository.deleteById(idEvent);
  }

  @Override
  public List<Event> findEventsByTypeId(UUID idTypeEvent) throws EventTypeNotFoundException {
    TypeEvent typeEvent = typeEventRepository.findById(idTypeEvent)
        .orElseThrow(() -> new EventTypeNotFoundException(idTypeEvent));
    return eventRepository.findAllByTypeEventOrderByStartTimeDesc(typeEvent);
  }

  @Override
  public List<Event> findEventsByDateRange(LocalDateTime start, LocalDateTime end)
      throws InvalidDateException {
    if (end.isBefore(start)) {
      throw new InvalidDateException();
    }
    return eventRepository.findByDateRangeOrderByStartTime(start, end);
  }

  @Override
  public List<Event> findAllEventsOrderedByStartTime() {
    return eventRepository.findAllByOrderByStartTime();
  }

  @Override
  public List<Event> findAllMyEventsByUserEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));
    List<Event> organizedEvents = eventRepository.findEventsByIdOrganizer(user.getIdUser());
    List<Event> participatedEvents = participationRepository.findEventsByIdIdUser(user.getIdUser());
    return Stream.concat(organizedEvents.stream(), participatedEvents.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  @Override
  public List<Event> findAllEventsOrderedByScore() {
    return eventRepository.findAllByOrderByScore();
  }

  @Override
  public List<Event> findEventsByLocationId(UUID idCity) throws LocationNotFoundException {
    Location location = locationRepository.findById(idCity)
        .orElseThrow(() -> new LocationNotFoundException(idCity));
    return eventRepository.findAllByLocationOrderByStartTimeDesc(location);
  }

  @Override
  public List<Event> searchEvents(SearchCriteria criteria) {
    Specification<Event> spec = new EventSpecification(criteria);
    Sort sort = Sort.by(
        Sort.Direction.DESC,
        criteria.getOrderBy().equals("score") ? "score" : "startTime");
    return eventRepository.findAll(spec, sort);
  }

  public class EventSpecification implements Specification<Event> {

    private SearchCriteria criteria;

    public EventSpecification(SearchCriteria criteria) {
      this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query,
        CriteriaBuilder builder) {
      List<Predicate> predicates = new ArrayList<>();

      if (criteria.getEventTypes() != null && !criteria.getEventTypes().isEmpty()) {
        predicates.add(root.get("typeEvent").get("name").in(criteria.getEventTypes()));
      }
      if (criteria.getCities() != null && !criteria.getCities().isEmpty()) {
        predicates.add(root.get("location").get("name").in(criteria.getCities()));
      }
      if (criteria.getLocationTypes() != null && !criteria.getLocationTypes().isEmpty()) {
        predicates.add(root.get("typeLocation").in(criteria.getLocationTypes()));
      }
      if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
        predicates.add(
            builder.between(root.get("startTime"), criteria.getStartDate(), criteria.getEndDate()));
      }

      return builder.and(predicates.toArray(new Predicate[0]));
    }
  }

}





