package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.EventRequest;
import com.dauphine.eventmanagement.models.Event;
import com.dauphine.eventmanagement.services.EventService;
import com.dauphine.eventmanagement.services.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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

  public EventController(EventServiceImpl eventServiceImpl) {
    this.eventService = eventServiceImpl;
  }

  @Operation(
      summary = "Get all events",
      description = "Retrieves a list of all events or filtered by title if provided."
  )
  public List<Event> getAllEvents(
      @Parameter(description = "Optional search string to filter event by title. If omitted, all events are returned.") @RequestParam(required = false) String title) {
    List<Event> events = title == null || title.isBlank()
        ? eventService.findAllEvents()
        : eventService.getAllLikeTitle(title);
    return events;
  }

  @GetMapping("/past")
  @Operation(
      summary = "Retrieve all past events",
      description = "Fetches a list of all past events."
  )
  public List<Event> getAllPastEvents() {
    return eventService.findPastEvents();
  }

  @GetMapping("/future")
  @Operation(
      summary = "Retrieve all future events",
      description = "Fetches a list of all future events."
  )
  public List<Event> getAllFutureEvents() {
    return eventService.findFutureEvents();
  }

  @GetMapping("/my")
  @Operation(
      summary = "Retrieve all events the user participates in",
      description = "Fetches a list of all events that the currently authenticated user participates in regardless of the event date."
  )
  public List<Event> getAllMyEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user participates in
    UUID id_organizer = null;
    return null;
  }

  @GetMapping("/my/past")
  @Operation(
      summary = "Retrieve all past events the user has participated in",
      description = "Fetches a list of all past events that the currently authenticated user has participated in."
  )
  public List<Event> getAllMyPastEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user has participated in

    return null;
  }

  @GetMapping("/my/future")
  @Operation(
      summary = "Retrieve all future events the user is scheduled to participate in",
      description = "Fetches a list of all future events that the currently authenticated user is scheduled to participate in."
  )
  public List<Event> getAllMyFutureEvents() {
    //Todo: get current user id et try to use particpationService to get all the event of the user will participate in
    return null;
  }


  @GetMapping("/{id}")
  @Operation(
      summary = "Get an event by its ID",
      description = "Fetches a single event by its unique identifier."
  )
  public Event getEventByID(@Parameter(description = "id of event") @PathVariable UUID id_event) {
    return eventService.findEventById(id_event);
  }

  @GetMapping("/type/{id_type_event}")
  @Operation(
      summary = "Get events by type ID",
      description = "Fetches all events that match a particular type ID."
  )
  public List<Event> getEventsByTypeId(
      @Parameter(description = "id of event's type") @PathVariable UUID id_type_event) {
    return eventService.findEventsByTypeId(id_type_event);
  }

  @GetMapping("/date")
  @Operation(
      summary = "Get events within a specific date range",
      description = "Fetches all events occurring within a specified start and end date."
  )

  public List<Event> getEventsByDateRange(
      @Parameter(description = "Start time of search") @RequestParam LocalDateTime search_start,
      @Parameter(description = "End time of search") @RequestParam LocalDateTime search_end) {
    return eventService.findEventsByDateRange(search_start, search_end);
  }

  @GetMapping("/location/{id_city}")
  @Operation(
      summary = "Get events by location ID",
      description = "Fetches all events associated with a specific location ID."
  )
  public List<Event> getEventsByLocationId(
      @Parameter(description = "id of city") @PathVariable UUID id_city) {
    return eventService.findEventsByLocationId(id_city);
  }


  @PostMapping
  @Operation(
      summary = "Create a new event",
      description = "Creates a new event with the provided details."
  )
  public Event createMyEvent(
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     UUID id_organizer = auth.getId(); */
    UUID id_organizer = null;
    return eventService.createEvent(eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStart_time(),
        eventRequest.getEnd_time(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId(),
        id_organizer);
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Update an existing event",
      description = "Updates an event identified by its ID with new details."
  )
  public Event updateEventId(@Parameter(description = "id of event") @PathVariable UUID id_event,
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    return eventService.updateEvent(id_event,
        eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStart_time(),
        eventRequest.getEnd_time(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId());
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Delete an event",
      description = "Deletes an event based on its ID,but only the event witch is organised bu current user can be deleted"
  )
  public void deleteMyEvent(@Parameter(description = "id of event") @PathVariable UUID id_event) {
    //Todo throw out exception when this event is not the current user organises
    eventService.deleteEvent(id_event);
  }

  @GetMapping("/sortedByStartDate")
  @Operation(
      summary = "Order events by start date",
      description = "Fetches all events ordered by their start dates."
  )
  public List<Event> orderEventsByStartDate() {
    return eventService.findAllEventsOrderedByStartDate();
  }

  @GetMapping("/sortedByNote")
  @Operation(
      summary = "Order events by moyen note of event",
      description = "Fetches all events ordered by their moyen note."
  )
  public List<Event> orderEventsByNote() {
    return eventService.findAllEventsOrderedByNote();
  }

}
