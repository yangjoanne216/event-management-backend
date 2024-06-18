package com.dauphine.eventmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "typeEvent")
public class TypeEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idType;

  @Column(nullable = false, unique = true)
  private String name;


  public UUID getIdType() {
    return idType;
  }

  public void setIdType(UUID idType) {
    this.idType = idType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}