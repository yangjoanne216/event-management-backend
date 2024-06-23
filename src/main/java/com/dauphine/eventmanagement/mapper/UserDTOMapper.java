package com.dauphine.eventmanagement.mapper;

import com.dauphine.eventmanagement.dto.SignUpDTO;
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


  public User signUpToUser(SignUpDTO signUpDTO) {
    User user = new User();
    user.setEmail(signUpDTO.email());  // 确保email不为null
    user.setFirstname(signUpDTO.firstname());
    user.setLastname(signUpDTO.lastname());
    user.setPassword(signUpDTO.password());  // 确保密码处理逻辑正确
    user.setAvatar(signUpDTO.avatar());
    return user;
  }
}
