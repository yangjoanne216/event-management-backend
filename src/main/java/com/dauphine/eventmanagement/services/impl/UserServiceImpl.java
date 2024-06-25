package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.exceptions.IncorrectPasswordException;
import com.dauphine.eventmanagement.exceptions.UserNotFoundException;
import com.dauphine.eventmanagement.mapper.UserDTOMapper;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.UserRepository;
import com.dauphine.eventmanagement.services.UserService;
import java.nio.CharBuffer;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDTOMapper userMapper;


  public UserDTO login(CredentialsDTO credentialsDTO) {
    User user = userRepository.findByEmail(credentialsDTO.email())
        .orElseThrow(() -> new RuntimeException("Unknown user"));

    if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()),
        user.getPassword())) {
      return userMapper.apply(user);
    }
    throw new RuntimeException("Invalid password");
  }


  public UserDTO register(SignUpDTO userDto) {
    Optional<User> optionalUser = userRepository.findByEmail(userDto.email());

    if (optionalUser.isPresent()) {
      throw new RuntimeException("Login already exists");
    }

    User user = userMapper.signUpToUser(userDto);
    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

    User savedUser = userRepository.save(user);

    return userMapper.apply(savedUser);
  }

  public String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDTO) {
      UserDTO user = (UserDTO) authentication.getPrincipal();
      return user.getEmail();
    }
    throw new IllegalStateException("User is not authenticated");
  }

  //get id of user by email
  public UUID getIdUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return user.get().getIdUser();
    } else {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
  }

  public UserDTO updateMyProfil(String email, String firstName, String lastName, String avatar)
      throws UserNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));

    user.setFirstname(firstName);
    user.setLastname(lastName);
    user.setAvatar(avatar);
    userRepository.save(user);

    return new UserDTO(user.getEmail(), user.getFirstname(), user.getLastname(), user.getAvatar());

  }


  public void updateMyPassword(String email, String oldPassword, String newPassword)
      throws UserNotFoundException, IncorrectPasswordException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));

    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new IncorrectPasswordException();
    }
    //froend  validates the new password against policy
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

}
