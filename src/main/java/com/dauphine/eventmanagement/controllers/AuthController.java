package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.config.UserAuthenticationProvider;
import com.dauphine.eventmanagement.dto.CredentialsDTO;
import com.dauphine.eventmanagement.dto.SignUpDTO;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.exceptions.IncorrectPasswordException;
import com.dauphine.eventmanagement.exceptions.UserNotFoundException;
import com.dauphine.eventmanagement.mapper.UserDTOMapper;
import com.dauphine.eventmanagement.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "Authentication API", description = "Users can log in, register, log out or update the profil.")
public class AuthController {

  private final UserServiceImpl userService;
  private final UserAuthenticationProvider userAuthenticationProvider;

  private final UserDTOMapper userDTOMapper;

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

  /*Effacez le JWT stocké localement sur le frontend.*/
  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    return ResponseEntity.ok().body("User logged out successfully");
  }

  @PutMapping("/updateMyProfile")
  @Operation(summary = "Update user profile", description = "Updates the profile details of the currently authenticated user.")
  public ResponseEntity<UserDTO> updateMyProfile(@RequestParam String firstName,
      @RequestParam String lastName, @RequestParam String avatar)
      throws UserNotFoundException, IncorrectPasswordException {
    String email = userService.getCurrentUserEmail();
    UserDTO updatedUser = userService.updateMyProfil(email, firstName, lastName, avatar);
    return ResponseEntity.ok(updatedUser);
  }

  @PutMapping("/updatePassword")
  @Operation(summary = "Update user password", description = "Updates the password of the currently authenticated user.")
  public ResponseEntity<String> updateMyPassword(@RequestParam String oldPassword,
      @RequestParam String newPassword)
      throws UserNotFoundException, IncorrectPasswordException {
    String email = userService.getCurrentUserEmail();
    userService.updateMyPassword(email, oldPassword, newPassword);
    return ResponseEntity.ok("Password updated successfully.");
  }

}
