package com.dauphine.eventmanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "participation")
public class Participation {

  @Id
  private UUID idEvent;

  @Id
  private UUID idUser;

  @ManyToOne
  @JoinColumn(name = "idEvent", referencedColumnName = "idEvent", insertable = false, updatable = false)
  private Event event;

  @ManyToOne
  @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
  private User user;

}
