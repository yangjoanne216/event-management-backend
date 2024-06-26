package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.FeedbackDTO;
import com.dauphine.eventmanagement.dto.FeedbackRequest;
import com.dauphine.eventmanagement.dto.FeedbackUpdateRequest;
import com.dauphine.eventmanagement.exceptions.EventNotFoundException;
import com.dauphine.eventmanagement.exceptions.UserNotFoundException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.FeedbackAlreadyExistsException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackAccessException;
import com.dauphine.eventmanagement.exceptions.feedbackExceptions.UnauthorizedFeedbackDeletionException;
import com.dauphine.eventmanagement.mapper.FeedbackDTOMapper;
import com.dauphine.eventmanagement.services.FeedbackService;
import com.dauphine.eventmanagement.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/feedback")
@Tag(name = "Feedback API", description = "Feedback endpoints for managing event feedbacks")
public class FeedbackController {

  private final FeedbackService feedbackService;
  private final FeedbackDTOMapper feedbackDTOMapper;

  private final UserService userService;

  public FeedbackController(FeedbackService feedbackService, FeedbackDTOMapper feedbackDTOMapper,
      UserService userService) {
    this.feedbackService = feedbackService;
    this.feedbackDTOMapper = feedbackDTOMapper;
    this.userService = userService;
  }

  @GetMapping("/event/{idEvent}")
  @Operation(summary = "Get all feedback for an event", description = "Retrieves all feedback entries submitted for a specific event.")
  public ResponseEntity<List<FeedbackDTO>> getAllFeedbackForEvent(
      @PathVariable UUID idEvent) {
    return ResponseEntity.ok(
        feedbackService.getAllFeedbackByEventId(idEvent).stream().map(feedbackDTOMapper::apply)
            .collect(Collectors.toList()));
  }

  @PostMapping
  @Operation(summary = "Create feedback", description = "Submits feedback for an event by current user.")
  public ResponseEntity<FeedbackDTO> createMyFeedback(
      @RequestBody FeedbackRequest feedbackRequest)
      throws UserNotFoundException, EventNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException {
    // get currentUser information
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    return ResponseEntity.ok(feedbackDTOMapper.apply(
        feedbackService.createMyFeedback(idUser, feedbackRequest.getIdEvent(),
            feedbackRequest.getContent(), feedbackRequest.getScore())));
  }

  @PutMapping("/{idEvent}")
  @Operation(summary = "Update existing feedback", description = "Updates the feedback submitted by a user for an event.")
  public FeedbackDTO updateMyFeedback(
      @PathVariable UUID idEvent,
      @RequestBody FeedbackUpdateRequest feedbackUpdateRequest)
      throws UserNotFoundException, UnauthorizedFeedbackAccessException, FeedbackAlreadyExistsException {
    // get current user information
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    return feedbackDTOMapper.apply(
        feedbackService.updateMyFeedback(idEvent, idUser, feedbackUpdateRequest.getContent(),
            feedbackUpdateRequest.getScore()));
  }

  @DeleteMapping("/{idEvent}")
  @Operation(summary = "Delete feedback", description = "Deletes a specific feedback entry for an event.Only the autor can do that")
  public ResponseEntity<String> deleteMyFeedback(
      @PathVariable UUID idEvent)
      throws UnauthorizedFeedbackDeletionException {
    //get current user information
    String email = userService.getCurrentUserEmail();
    UUID idUser = userService.getIdUserByEmail(email);
    feedbackService.deleteMyFeedback(idEvent, idUser);
    return ResponseEntity.ok(" Feedback deleted successfully");
  }


}
