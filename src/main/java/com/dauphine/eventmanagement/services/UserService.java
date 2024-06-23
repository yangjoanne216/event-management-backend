package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import java.util.UUID;

public interface UserService {

  public UserDTO login(CredentialsDTO credentialsDTO);

  public UserDTO register(SignUpDTO userDto);

  String getCurrentUserEmail();

  UUID getIdUserByEmail(String email);
}
