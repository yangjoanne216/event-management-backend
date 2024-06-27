package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.exceptions.userExceptions.AuthenticationException;
import com.dauphine.eventmanagement.exceptions.userExceptions.EmailAlreadyExistsException;
import com.dauphine.eventmanagement.exceptions.userExceptions.IncorrectPasswordException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDTOMapper userMapper;


  public UserDTO login(CredentialsDTO credentialsDTO) throws AuthenticationException {
    User user = userRepository.findByEmail(credentialsDTO.email())
        .orElseThrow(() -> new AuthenticationException("Unknown user"));

    if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()),
        user.getPassword())) {
      return userMapper.apply(user);
    }
    throw new AuthenticationException("Invalid password");
  }


  public UserDTO register(SignUpDTO userDto) throws EmailAlreadyExistsException {
    String email = userDto.email();
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isPresent()) {
      throw new EmailAlreadyExistsException(email);
    }
    User user = userMapper.signUpToUser(userDto);
    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

    User savedUser = userRepository.save(user);

    return userMapper.apply(savedUser);
  }

  public String getCurrentUserEmail() throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDTO) {
      UserDTO user = (UserDTO) authentication.getPrincipal();
      return user.getEmail();
    }
    throw new AuthenticationException("User is not authenticated.");
  }

  //get id of user by email
  public UUID getIdUserByEmail(String email) throws UserNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return user.get().getIdUser();
    } else {
      throw new UserNotFoundException("User not found: " + email);
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

  public UserDTO getCurrentUser() throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = ((UserDTO) authentication.getPrincipal()).getEmail();
    User user = userRepository.findByEmail(email).orElseThrow(() ->
        new AuthenticationException("User not found with email: " + email));
    return new UserDTO(user.getEmail(), user.getFirstname(), user.getLastname(), user.getAvatar());
  }
}
