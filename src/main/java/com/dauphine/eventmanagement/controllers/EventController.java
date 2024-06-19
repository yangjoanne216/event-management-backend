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

  @GetMapping
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
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
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


  @GetMapping("/{idEvent}")
  @Operation(
      summary = "Get an event by its ID",
      description = "Fetches a single event by its unique identifier."
  )
  public Event getEventByID(@Parameter(description = "id of event") @PathVariable UUID idEvent) {
    return eventService.findEventById(idEvent);
  }

  //Todo : change the url : /v1/events/type/{idTypeEvent} to /v1/types/{idTypeEvent}/events
  @GetMapping("/type/{idTypeEvent}")
  @Operation(
      summary = "Get events by type ID",
      description = "Fetches all events that match a particular type ID."
  )
  public List<Event> getEventsByTypeId(
      @Parameter(description = "id of event's type") @PathVariable UUID idTypeEvent) {
    return eventService.findEventsByTypeId(idTypeEvent);
  }

  @GetMapping("/date")
  @Operation(
      summary = "Get events within a specific date range",
      description = "Fetches all events occurring within a specified start and end date."
  )

  public List<Event> getEventsByDateRange(
      @Parameter(description = "Start time of search") @RequestParam LocalDateTime searchStart,
      @Parameter(description = "End time of search") @RequestParam LocalDateTime searchEnd) {
    return eventService.findEventsByDateRange(searchStart, searchEnd);
  }

  @GetMapping("/location/{idCity}")
  @Operation(
      summary = "Get events by location ID",
      description = "Fetches all events associated with a specific location ID."
  )
  public List<Event> getEventsByLocationId(
      @Parameter(description = "id of city") @PathVariable UUID idCity) {
    return eventService.findEventsByLocationId(idCity);
  }


  @PostMapping
  @Operation(
      summary = "Create a new event",
      description = "Creates a new event with the provided details."
  )
  public Event createMyEvent(
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    /*//Todo:Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     UUID idOrganizer = auth.getId(); */
    //assumer yang yang is current user
    UUID idOrganizer = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return eventService.createEvent(eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId(),
        idOrganizer);
  }

  @PutMapping("/{idEvent}")
  @Operation(
      summary = "Update an existing event",
      description = "Updates an event identified by its ID with new details."
  )
  public Event updateEventId(@Parameter(description = "id of event") @PathVariable UUID idEvent,
      @Parameter(description = "Title,description,start time,end time, id of event,type of Location, Url for Image, id of city(location)") @RequestBody EventRequest eventRequest) {
    return eventService.updateEvent(idEvent,
        eventRequest.getTitle(),
        eventRequest.getDescription(),
        eventRequest.getStartTime(),
        eventRequest.getEndTime(),
        eventRequest.getTypeEventId(),
        eventRequest.getTypeLocation(),
        eventRequest.getImage(),
        eventRequest.getLocationId());
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
  public List<Event> orderEventsByStartTime() {
    return eventService.findAllEventsOrderedByStartTime();
  }


  @GetMapping("/sortedByScore")
  @Operation(
      summary = "Order events by moyen note of event",
      description = "Fetches all events ordered by their moyen note."
  )
  public List<Event> orderEventsByScore() {
    return eventService.findAllEventsOrderedByScore();
  }

}
