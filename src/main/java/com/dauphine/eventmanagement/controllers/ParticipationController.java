package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.services.ParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/participation")
@Tag(
    name = "Participation API",
    description = "Participation endPoints"
)
public class ParticipationController {

  private final ParticipationService participationService;

  public ParticipationController(ParticipationService participationService) {
    this.participationService = participationService;
  }

  @PostMapping("/participate")
  @Operation(
      summary = "Current user participates in an event",
      description = "Registers the user for the specified event by their IDs."
  )
  public ResponseEntity<String> participate(
      @Parameter(description = "id of event") @RequestParam UUID id_event) {
    //Todo get current user id
    UUID id_user = null;
    participationService.participate(id_user, id_event);
    return ResponseEntity.ok("Participation registered successfully.");
  }

  @DeleteMapping("/cancel")
  @Operation(
      summary = "Cancel participation in an event",
      description = "Cancels the user's registration for the specified event by their IDs."
  )
  public ResponseEntity<String> cancelParticipation(
      @Parameter(description = "id of event") @RequestParam UUID id_event) {
    //Todo get current user id
    UUID id_user = null;
    participationService.cancelParticipation(id_user, id_event);
    return ResponseEntity.ok("Participation cancelled successfully.");
  }

  @GetMapping("/participants/{idEvent}")
  @Operation(
      summary = "Get all participants of an event",
      description = "Retrieves a list of all participants registered for the specified event."
  )
  public ResponseEntity<List<User>> getParticipants(
      @Parameter(description = "id of event") @PathVariable UUID idEvent) {
    List<User> participants = participationService.getParticipants(idEvent);
    return ResponseEntity.ok(participants);
  }
}

