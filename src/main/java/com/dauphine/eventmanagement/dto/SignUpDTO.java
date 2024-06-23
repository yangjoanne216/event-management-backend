package com.dauphine.eventmanagement.dto;

public record SignUpDTO(String email, String firstname, String lastname,
                        String avatar,
                        String password) {

  @Override
  public String email() {
    return email;
  }

  @Override
  public String firstname() {
    return firstname;
  }

  @Override
  public String lastname() {
    return lastname;
  }

  @Override
  public String avatar() {
    return avatar;
  }

  @Override
  public String password() {
    return password;
  }
}
