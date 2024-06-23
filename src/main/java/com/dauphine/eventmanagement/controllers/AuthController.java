package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.config.UserAuthenticationProvider;
import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

  private final UserServiceImpl userService;
  private final UserAuthenticationProvider userAuthenticationProvider;

  @PostMapping("/login")
  public UserDTO login(@RequestBody CredentialsDTO credentialsDTO) {
    UserDTO user = userService.login(credentialsDTO);
    user.setToken(userAuthenticationProvider.createToken(user));
    return user;
  }


  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody @Valid SignUpDTO user) {
    UserDTO createdUser = userService.register(user);
    createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
    return ResponseEntity.created(URI.create("/users/" + createdUser.getLastname()))
        .body(createdUser);
  }

  /*Todo: Effacez le JWT stocké localement sur le frontend.*/
  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    return ResponseEntity.ok().body("User logged out successfully");
  }


}
