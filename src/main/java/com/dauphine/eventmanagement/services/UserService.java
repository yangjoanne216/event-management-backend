package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.exceptions.userExceptions.IncorrectPasswordException;
import com.dauphine.eventmanagement.exceptions.userExceptions.UserNotFoundException;
import java.util.UUID;

public interface UserService {

  UserDTO login(CredentialsDTO credentialsDTO);

  UserDTO register(SignUpDTO userDto);

  String getCurrentUserEmail();

  UUID getIdUserByEmail(String email);

  UserDTO updateMyProfil(String email, String firstName, String lastName, String avatar)
      throws UserNotFoundException;

  void updateMyPassword(String email, String oldPassword, String password)
      throws UserNotFoundException, IncorrectPasswordException;
}
