package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.ParticipateEventRequest;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.eventExceptions.EventTimePastException;
import com.dauphine.eventmanagement.exceptions.participationExceptions.NotParticipantException;
import com.dauphine.eventmanagement.mapper.UserDTOMapper;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.services.ParticipationService;
import com.dauphine.eventmanagement.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/participation")
@Tag(
    name = "Participation API",
    description = "Participation endPoints"
)
public class ParticipationController {

  private final ParticipationService participationService;
  private final UserService userService;
  private final UserDTOMapper userDTOMapper;

  public ParticipationController(ParticipationService participationService,
      UserService userService, UserDTOMapper userDTOMapper) {
    this.participationService = participationService;
    this.userService = userService;
    this.userDTOMapper = userDTOMapper;
  }

  @PostMapping("/participate")
  @Operation(
      summary = "Current user participates in an event",
      description = "Registers the user for the specified event by their IDs."
  )
  public ResponseEntity<String> participate(
      @Parameter(description = "id of event") @RequestBody ParticipateEventRequest participateEventRequest)
      throws NotParticipantException, EventTimePastException {
    // getCurrent User Information
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    participationService.participate(idUser, participateEventRequest.getIdEvent());
    return ResponseEntity.ok("Participation registered successfully.");
  }

  @DeleteMapping("/cancel/{idEvent}")
  @Operation(
      summary = "Current user cancels participation in an event",
      description = "Cancels the user's registration for the specified event by their IDs."
  )
  public ResponseEntity<String> cancelMyParticipation(
      @Parameter(description = "id of event") @PathVariable UUID idEvent)
      throws NotParticipantException, EventTimePastException {
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    participationService.cancelParticipation(idUser, idEvent);
    return ResponseEntity.ok("Participation cancelled successfully.");
  }

  @GetMapping("/participants/{idEvent}")
  @Operation(
      summary = "Get all participants of an event",
      description = "Retrieves a list of all participants registered for the specified event."
  )
  public ResponseEntity<List<UserDTO>> getParticipants(
      @Parameter(description = "id of event") @PathVariable UUID idEvent)
      throws EventNotFoundException {
    List<User> participants = participationService.getParticipants(idEvent);
    return ResponseEntity.ok(participants.stream().map(userDTOMapper::apply)
        .collect(Collectors.toList()));
  }
}

