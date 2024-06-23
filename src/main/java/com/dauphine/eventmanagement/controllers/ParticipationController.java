package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.UserDTO;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Todo: add endpoints for admin role
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
  public String participate(
      @Parameter(description = "id of event") @RequestParam UUID idEvent) {
    // 获取当前认证信息
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    participationService.participate(idUser, idEvent);
    return "Participation registered successfully.";
  }

  @DeleteMapping("/cancel")
  @Operation(
      summary = "Cancel participation in an event",
      description = "Cancels the user's registration for the specified event by their IDs."
  )
  public String cancelMyParticipation(
      @Parameter(description = "id of event") @RequestParam UUID id_event) {
    //不能取消已经时间超过今天的event
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    participationService.cancelParticipation(idUser, id_event);
    return "Participation cancelled successfully.";
  }

  @GetMapping("/participants/{idEvent}")
  @Operation(
      summary = "Get all participants of an event",
      description = "Retrieves a list of all participants registered for the specified event."
  )
  public List<UserDTO> getParticipants(
      @Parameter(description = "id of event") @PathVariable UUID idEvent) {
    //Todo Exception pas de id of event
    List<User> participants = participationService.getParticipants(idEvent);
    return participants.stream().map(userDTOMapper::apply)
        .collect(Collectors.toList());
  }
}

