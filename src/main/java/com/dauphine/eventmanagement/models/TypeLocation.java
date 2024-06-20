package com.dauphine.eventmanagement.models;

public enum TypeLocation {

  ONLINE("Online"),
  ONSITE("Onsite"),
  HYBRID("Hybrid");

  private final String name;

  TypeLocation(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
