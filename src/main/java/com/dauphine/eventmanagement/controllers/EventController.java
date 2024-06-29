package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.EventDTO;
import com.dauphine.eventmanagement.dto.EventRequest;
import com.dauphine.eventmanagement.dto.SearchCriteria;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTypeNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.InvalidDateException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.LocationNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.UnauthorizedEventModificationException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import com.dauphine.eventmanagement.mapper.EventDTOMapper;
import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.services.EventService;
import com.dauphine.eventmanagement.services.UserService;
import com.dauphine.eventmanagement.services.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/events")
@Tag(
    name = "Events API",
    description = "Events endPoints"
)
public class EventController {

  private final EventService eventService;
  private final UserService userService;
  private final EventDTOMapper eventDTOMapper;

  public EventController(EventServiceImpl eventServiceImpl, UserService userService,
      EventDTOMapper eventDTOMapper) {
    this.eventService = eventServiceImpl;
    this.userService = userService;
    this.eventDTOMapper = eventDTOMapper;
  }

  @GetMapping
  @Operation(
      summary = "Get all events",
      description = "Retrieves a list of all events or filtered by title if provided."
  )
  public ResponseEntity<List<EventDTO>> getAllEvents(
      @Parameter(description = "Optional search string to filter event by title. If omitted, all events are returned.") @RequestParam(required = false) String title) {
    List<Event> events = title == null || title.isBlank()
        ? eventService.findAllEvents()
        : eventService.getAllLikeTitle(title);
    List<EventDTO> eventDTOS = events.stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
    return ResponseEntity.ok(eventDTOS);
  }

  @GetMapping("/my")
  @Operation(
      summary = "Current User: Retrieve all events the current user participates in or organizes",
      description = "Fetches a list of all events that the currently authenticated user participates in or organizes"
  )
  public ResponseEntity<List<EventDTO>> getAllMyEvents() {
    String email = userService.getCurrentUserEmail();
    List<EventDTO> eventDTOS = eventService.findAllMyEventsByUserEmail(email).stream()
        .map(eventDTOMapper::apply)
        .collect(Collectors.toList());
    return ResponseEntity.ok(eventDTOS);

  }

  @GetMapping("/{idEvent}")
  @Operation(
      summary = "Get an event by its ID",
      description = "Fetches a single event by its unique identifier."
  )
  public EventDTO getEventByID(@Parameter(description = "id of event") @PathVariable UUID idEvent)
      throws EventNotFoundException {
    return eventDTOMapper.apply(eventService.findEventById(idEvent));
  }

  @GetMapping("/type/{idTypeEvent}")
  @Operation(
      summary = "Get events by type ID",
      description = "Fetches all events that match a particular type ID."
  )
  public ResponseEntity<List<EventDTO>> getEventsByTypeId(
      @Parameter(description = "id of event's type") @PathVariable UUID idTypeEvent)
      throws EventNotFoundException {
    return ResponseEntity.ok(
        eventService.findEventsByTypeId(idTypeEvent).stream().map(eventDTOMapper::apply)
            .collect(Collectors.toList()));
  }

  @GetMapping("/date")
  @Operation(
      summary = "Get events within a specific date range",
      description = "Fetches all events occurring within a specified start and end date."
  )

  public ResponseEntity<List<EventDTO>> getEventsByDateRange(
      @Parameter(description = "Start time of search") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime searchStart,
      @Parameter(description = "End time of search") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime searchEnd)
      throws InvalidDateException {
    List<EventDTO> eventDTOS = eventService.findEventsByDateRange(searchStart, searchEnd).stream()
        .map(eventDTOMapper::apply).collect(Collectors.toList());
    return ResponseEntity.ok(eventDTOS);
  }

  @GetMapping("/location/{idCity}")
  @Operation(
      summary = "Get events by location ID",
      description = "Fetches all events associated with a specific location ID."
  )
  public ResponseEntity<List<EventDTO>> getEventsByLocationId(
      @Parameter(description = "id of city") @PathVariable UUID idCity)
      throws LocationNotFoundException {
    return ResponseEntity.ok(
        eventService.findEventsByLocationId(idCity).stream().map(eventDTOMapper::apply)
            .collect(Collectors.toList()));
  }


  @PostMapping
  @Operation(
      summary = "Current user : create a new event",
      description = "Creates a new event with the provided details."
  )
  public ResponseEntity<EventDTO> createMyEvent(
      @RequestBody @Parameter(description = "The event request containing all necessary event details.") EventRequest eventRequest)
      throws InvalidDateException, EventTypeNotFoundException, UserNotFoundException, LocationNotFoundException, EventTimePastException {
    String email = userService.getCurrentUserEmail();
    UUID idOrganizer = userService.getIdUserByEmail(email);
    EventDTO event = eventDTOMapper.apply(eventService.createMyEvent(
        eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventName(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationName(),
        idOrganizer
    ));
    return ResponseEntity.ok(event);
  }

  @PutMapping("/{idEvent}")
  @Operation(
      summary = "Update an existing event",
      description = "Updates an event identified by its ID with new details."
  )
  public ResponseEntity<EventDTO> updateMyEvent(
      @Parameter(description = "id of event") @PathVariable UUID idEvent,
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest)
      throws UnauthorizedEventModificationException, EventNotFoundException, InvalidDateException, EventTypeNotFoundException, LocationNotFoundException, EventTimePastException {
    return ResponseEntity.ok(eventDTOMapper.apply(eventService.updateMyEvent(idEvent,
        eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventName(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationName())));
  }

  @DeleteMapping("/{idEvent}")
  @Operation(
      summary = "Delete an event",
      description = "Deletes an event based on its ID,but only the event witch is organised bu current user can be deleted"
  )
  public ResponseEntity<Void> deleteMyEvent(
      @Parameter(description = "id of event") @PathVariable UUID idEvent)
      throws EventNotFoundException, UnauthorizedEventModificationException {
    eventService.deleteMyEvent(idEvent);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/sortedByStartTimeAsc")
  @Operation(
      summary = "Order events by start time old to new",
      description = "Fetches all events ordered by their start times."
  )
  public ResponseEntity<List<EventDTO>> orderEventsByStartTimeAsc() {
    return ResponseEntity.ok(
        eventService.findAllEventsOrderedByStartTime().stream().map(eventDTOMapper::apply)
            .collect(Collectors.toList()));
  }


  @GetMapping("/sortedByScore")
  @Operation(
      summary = "Order events by moyen note of event(Desc) ",
      description = "Fetches all events ordered by their moyen note."
  )
  public ResponseEntity<List<EventDTO>> orderEventsByScoreDesc() {
    return ResponseEntity.ok(
        eventService.findAllEventsOrderedByScore().stream().map(eventDTOMapper::apply)
            .collect(Collectors.toList()));
  }

  /*orderBy：date date*/
  @GetMapping("/search")
  public ResponseEntity<List<EventDTO>> searchEvents(
      @RequestParam(required = false) List<String> eventTypes,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
      @RequestParam(required = false) List<String> cities,
      @RequestParam(required = false) List<String> locationTypes,
      @RequestParam(required = false, defaultValue = "startTime") String orderBy) {
    SearchCriteria criteria = new SearchCriteria(eventTypes, startDate, endDate, cities,
        locationTypes);
    List<Event> events = eventService.searchEvents(criteria, orderBy);
    if ("score".equalsIgnoreCase(orderBy)) {
      events.sort(
          Comparator.comparing(Event::getScore, Comparator.nullsLast(Double::compareTo))
              .reversed());
    }
    List<EventDTO> eventDTOs = events.stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
    return ResponseEntity.ok(eventDTOs);
  }

}
