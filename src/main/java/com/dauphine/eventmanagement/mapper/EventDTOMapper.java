package com.dauphine.eventmanagement.mapper;

import com.dauphine.eventmanagement.dto.EventDTO;
import com.dauphine.eventmanagement.dto.FeedbackDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.models.Event;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EventDTOMapper implements Function<Event, EventDTO> {

  private final UserDTOMapper userDTOMapper = new UserDTOMapper();
  private final FeedbackDTOMapper feedbackDTOMapper = new FeedbackDTOMapper();

  @Override
  public EventDTO apply(Event event) {
    UserDTO organizer = userDTOMapper.apply(event.getOrganizer());
    List<UserDTO> participants = (event.getParticipants() != null ? event.getParticipants().stream()
        .map(participation -> userDTOMapper.apply(participation.getUser()))
        .collect(Collectors.toList()) : Collections.emptyList());
    List<FeedbackDTO> feedbacks = (event.getFeedbacks() != null ? event.getFeedbacks().stream()
        .map(feedbackDTOMapper)
        .collect(Collectors.toList()) : Collections.emptyList());
    return new EventDTO(event.getIdEvent(),
        event.getTitle(),
        event.getDescription(),
        event.getStartTime(),
        event.getEndTime(),
        event.getTypeEvent().getName(),
        event.getTypeLocation().getName(),
        (event.getLocation() != null ? event.getLocation().getName() : ""),
        organizer, participants, feedbacks, event.getScore(),
        event.getImage());
  }
}
