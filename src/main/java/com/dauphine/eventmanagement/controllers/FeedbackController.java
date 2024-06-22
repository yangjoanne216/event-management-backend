package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.dto.FeedbackDTO;
import com.dauphine.eventmanagement.dto.FeedbackRequest;
import com.dauphine.eventmanagement.dto.FeedbackUpdateRequest;
import com.dauphine.eventmanagement.mapper.FeedbackDTOMapper;
import com.dauphine.eventmanagement.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/feedback")
@Tag(name = "Feedback API", description = "Feedback endpoints for managing event feedbacks")
public class FeedbackController {

  private final FeedbackService feedbackService;
  private final FeedbackDTOMapper feedbackDTOMapper;

  public FeedbackController(FeedbackService feedbackService, FeedbackDTOMapper feedbackDTOMapper) {
    this.feedbackService = feedbackService;
    this.feedbackDTOMapper = feedbackDTOMapper;
  }

  @PostMapping
  @Operation(summary = "Create feedback", description = "Submits feedback for an event by a user.")
  public FeedbackDTO createFeedback(
      @RequestBody FeedbackRequest feedbackRequest) {
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return feedbackDTOMapper.apply(
        feedbackService.createFeedback(idUser, feedbackRequest.getIdEvent(),
            feedbackRequest.getContent(), feedbackRequest.getScore()));
  }

  @PutMapping("/{idEvent}")
  @Operation(summary = "Update existing feedback", description = "Updates the feedback submitted by a user for an event.")
  public FeedbackDTO updateFeedback(
      @PathVariable UUID idEvent,
      @RequestBody FeedbackUpdateRequest feedbackUpdateRequest) {
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    return feedbackDTOMapper.apply(
        feedbackService.updateFeedback(idEvent, idUser, feedbackUpdateRequest.getContent(),
            feedbackUpdateRequest.getScore()));
  }

  @DeleteMapping("/{idEvent}")
  @Operation(summary = "Delete feedback", description = "Deletes a specific feedback entry for an event.Only the autor can do that")
  public void deleteFeedback(
      @PathVariable UUID idEvent
  ) {
    //assumer yang yang is current user
    UUID idUser = UUID.fromString("58bdba14-9cec-4f39-bc27-43a01afef3ae");
    feedbackService.deleteFeedback(idEvent, idUser);
  }

  @GetMapping("/event/{idEvent}")
  @Operation(summary = "Get all feedback for an event", description = "Retrieves all feedback entries submitted for a specific event.")
  public List<FeedbackDTO> getAllFeedbackForEvent(
      @PathVariable UUID idEvent) {
    return feedbackService.getAllFeedbackByEventId(idEvent).stream().map(feedbackDTOMapper::apply)
        .collect(Collectors.toList());
  }
}
