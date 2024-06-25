package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.models.TypeEvent;
import com.dauphine.eventmanagement.services.TypeEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/typesEvent")
@Tag(
    name = "Type Event API",
    description = "Type of Event , "
        + "currently we have 'Conference', 'Workshop', 'Meetup'"
)
public class TypeEventController {

  private final TypeEventService typeEventService;

  public TypeEventController(TypeEventService typeEventService) {
    this.typeEventService = typeEventService;
  }

  @GetMapping
  @Operation(
      summary = "Retrieve all type events",
      description = "Fetches a list of all types of events."
  )
  public ResponseEntity<List<TypeEvent>> getAllTypesEvent() {
    List<TypeEvent> typesEvents = typeEventService.getAllTypesEvent();
    return ResponseEntity.ok(typesEvents);
  }
}
