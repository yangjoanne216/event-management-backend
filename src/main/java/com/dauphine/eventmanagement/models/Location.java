package com.dauphine.eventmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_city;

  @Column(nullable = false, unique = true)
  private String name;


  public Long getId_city() {
    return id_city;
  }

  public void setId_city(Long id_city) {
    this.id_city = id_city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}