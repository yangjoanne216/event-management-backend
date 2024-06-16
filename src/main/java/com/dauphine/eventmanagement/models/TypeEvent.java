package com.dauphine.eventmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "type_event")
public class TypeEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id_type;

  @Column(nullable = false, unique = true)
  private String name;


  public UUID getId_type() {
    return id_type;
  }

  public void setId_type(UUID id_type) {
    this.id_type = id_type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}