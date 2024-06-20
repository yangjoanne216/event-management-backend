package com.dauphine.eventmanagement.mapper;

import com.dauphine.eventmanagement.dto.FeedbackDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.models.Feedback;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class FeedbackDTOMapper implements Function<Feedback, FeedbackDTO> {

  private final UserDTOMapper userDTOMapper = new UserDTOMapper();


  @Override
  public FeedbackDTO apply(Feedback feedback) {
    UserDTO userDto = userDTOMapper.apply(feedback.getUser());
    return new FeedbackDTO(feedback.getIdFeedback().getIdEvent(), userDto, feedback.getDate(),
        feedback.getContent(),
        feedback.getScore());
  }
}
