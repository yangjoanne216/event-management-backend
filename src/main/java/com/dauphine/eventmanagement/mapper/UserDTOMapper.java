package com.dauphine.eventmanagement.mapper;

import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.models.User;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

  @Override
  public UserDTO apply(User user) {
    return new UserDTO(user.getEmail(), user.getFirstname(), user.getLastname(), user.getAvatar());
  }
}
