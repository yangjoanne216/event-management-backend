package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.FeedbackRequest;
import com.dauphine.eventmanagement.models.Feedback;
import com.dauphine.eventmanagement.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/feedbacks")
@Tag(
    name = "Feedback API",
    description = "Feedback endPoints"
)
public class FeedbackController {

  private final FeedbackService feedbackService;

  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  @PostMapping
  @Operation(
      summary = "Create feedback for an event",
      description = "Submits feedback for an event by current user."
  )
  public Feedback createFeedback(
      @Parameter(description = "id of event, content of feedbocak, note for this event") @RequestBody FeedbackRequest feedbackRequest) {
    //Todo get current user id
    UUID idUser = null;
    Feedback createdFeedback = feedbackService.createFeedback(idUser,
        feedbackRequest.getIdEvent(), feedbackRequest.getContent(), feedbackRequest.getScore());
    return createdFeedback;
  }

  @PutMapping("/{idFeedback}")
  @Operation(
      summary = "Modify existing feedback",
      description = "Updates the feedback submitted by a user for an event."
  )
  public Feedback modifyFeedback(@PathVariable UUID idFeedback,
      @Parameter(description = "id of event, content of feedbocak, note for this event") @RequestBody FeedbackRequest feedbackRequest) {
    //Todo get current user id
    UUID idUser = null;
    Feedback updatedFeedback = feedbackService.modifyFeedback(idUser,
        feedbackRequest.getIdEvent(), feedbackRequest.getContent(), feedbackRequest.getScore());
    return updatedFeedback;
  }

  @DeleteMapping("/{idFeedback}")
  @Operation(
      summary = "Delete feedback",
      description = "Deletes a specific feedback entry for an event."
  )
  public Boolean deleteFeedback(
      @Parameter(description = "id of feedback") @PathVariable UUID idFeedback) {
    feedbackService.deleteFeedback(idFeedback);
    return true;
  }

  @GetMapping("/event/{idEvent}")
  @Operation(
      summary = "Get all feedback for an event",
      description = "Retrieves all feedback entries submitted for a specific event."
  )
  public List<Feedback> getAllFeedbackOfAnEvent(
      @Parameter(description = "id of event") @PathVariable UUID idEvent) {
    List<Feedback> feedbacks = feedbackService.getAllFeedbackOfAnEvent(idEvent);
    return feedbacks;
  }
}
