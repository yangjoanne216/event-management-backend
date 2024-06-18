package com.dauphine.eventmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idCity;

  @Column(nullable = false, unique = true)
  private String name;


  public UUID getIdCity() {
    return idCity;
  }

  public void setIdCity(UUID idCity) {
    this.idCity = idCity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}