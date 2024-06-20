package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.EventDTO;
import com.dauphine.eventmanagement.dto.EventRequest;
import com.dauphine.eventmanagement.mapper.EventDTOMapper;
import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.services.EventService;
import com.dauphine.eventmanagement.services.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
  private final EventDTOMapper eventDTOMapper;

  public EventController(EventServiceImpl eventServiceImpl, EventDTOMapper eventDTOMapper) {
    this.eventService = eventServiceImpl;
    this.eventDTOMapper = eventDTOMapper;
  }

  @GetMapping
  @Operation(
      summary = "Get all events",
      description = "Retrieves a list of all events or filtered by title if provided."
  )
  public List<EventDTO> getAllEvents(
      @Parameter(description = "Optional search string to filter event by title. If omitted, all events are returned.") @RequestParam(required = false) String title) {
    List<Event> events = title == null || title.isBlank()
        ? eventService.findAllEvents()
        : eventService.getAllLikeTitle(title);
    return events.stream().map(eventDTOMapper::apply).collect(Collectors.toList());
  }

  @GetMapping("/past")
  @Operation(
      summary = "Retrieve all past events",
      description = "Fetches a list of all past events."
  )
  public List<EventDTO> getAllPastEvents() {
    return eventService.findPastEvents().stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

  @GetMapping("/future")
  @Operation(
      summary = "Retrieve all future events",
      description = "Fetches a list of all future events."
  )
  public List<EventDTO> getAllFutureEvents() {
    return eventService.findFutureEvents().stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

  @GetMapping("/my")
  @Operation(
      summary = "Retrieve all events the user participates in",
      description = "Fetches a list of all events that the currently authenticated user participates in regardless of the event date."
  )
  public List<EventDTO> getAllMyEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user participates in
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return eventService.findEventsByIdUser(idUser).stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

  @GetMapping("/my/past")
  @Operation(
      summary = "Retrieve all past events the user has participated in",
      description = "Fetches a list of all past events that the currently authenticated user has participated in."
  )
  public List<EventDTO> getAllMyPastEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user has participated in
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return eventService.findPastEventsByIdUser(idUser).stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

  @GetMapping("/my/future")
  @Operation(
      summary = "Retrieve all future events the user is scheduled to participate in",
      description = "Fetches a list of all future events that the currently authenticated user is scheduled to participate in."
  )
  public List<EventDTO> getAllMyFutureEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user will participate in
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return eventService.findFutureEventsByIdUser(idUser).stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }


  @GetMapping("/{idEvent}")
  @Operation(
      summary = "Get an event by its ID",
      description = "Fetches a single event by its unique identifier."
  )
  public EventDTO getEventByID(@Parameter(description = "id of event") @PathVariable UUID idEvent) {
    return eventDTOMapper.apply(eventService.findEventById(idEvent));
  }

  //Todo : change the url : /v1/events/type/{idTypeEvent} to /v1/types/{idTypeEvent}/events
  @GetMapping("/type/{idTypeEvent}")
  @Operation(
      summary = "Get events by type ID",
      description = "Fetches all events that match a particular type ID."
  )
  public List<EventDTO> getEventsByTypeId(
      @Parameter(description = "id of event's type") @PathVariable UUID idTypeEvent) {
    return eventService.findEventsByTypeId(idTypeEvent).stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

  @GetMapping("/date")
  @Operation(
      summary = "Get events within a specific date range",
      description = "Fetches all events occurring within a specified start and end date."
  )

  public List<EventDTO> getEventsByDateRange(
      @Parameter(description = "Start time of search") @RequestParam LocalDateTime searchStart,
      @Parameter(description = "End time of search") @RequestParam LocalDateTime searchEnd) {
    return eventService.findEventsByDateRange(searchStart, searchEnd).stream()
        .map(eventDTOMapper::apply).collect(Collectors.toList());
  }

  @GetMapping("/location/{idCity}")
  @Operation(
      summary = "Get events by location ID",
      description = "Fetches all events associated with a specific location ID."
  )
  public List<EventDTO> getEventsByLocationId(
      @Parameter(description = "id of city") @PathVariable UUID idCity) {
    return eventService.findEventsByLocationId(idCity).stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }


  @PostMapping
  @Operation(
      summary = "Create a new event",
      description = "Creates a new event with the provided details."
  )
  public EventDTO createMyEvent(
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    /*//Todo:Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     UUID idOrganizer = auth.getId(); */
    //assumer yang yang is current user
    UUID idOrganizer = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return eventDTOMapper.apply(eventService.createEvent((eventRequest.getTitle()),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId(),
        idOrganizer));
  }

  @PutMapping("/{idEvent}")
  @Operation(
      summary = "Update an existing event",
      description = "Updates an event identified by its ID with new details."
  )
  public EventDTO updateEventId(@Parameter(description = "id of event") @PathVariable UUID idEvent,
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    return eventDTOMapper.apply(eventService.updateEvent(idEvent,
        eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId()));
  }

  @DeleteMapping("/{idEvent}")
  @Operation(
      summary = "Delete an event",
      description = "Deletes an event based on its ID,but only the event witch is organised bu current user can be deleted"
  )
  public void deleteMyEvent(@Parameter(description = "id of event") @PathVariable UUID idEvent) {
    //Todo throw out exception when this event is not the current user organises
    eventService.deleteEvent(idEvent);
  }

  @GetMapping("/sortedByStartTime")
  @Operation(
      summary = "Order events by start time",
      description = "Fetches all events ordered by their start times."
  )
  public List<EventDTO> orderEventsByStartTime() {
    return eventService.findAllEventsOrderedByStartTime().stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }


  @GetMapping("/sortedByScore")
  @Operation(
      summary = "Order events by moyen note of event",
      description = "Fetches all events ordered by their moyen note."
  )
  public List<EventDTO> orderEventsByScore() {
    return eventService.findAllEventsOrderedByScore().stream().map(eventDTOMapper::apply)
        .collect(Collectors.toList());
  }

}
