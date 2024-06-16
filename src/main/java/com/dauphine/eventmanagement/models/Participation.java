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
  private UUID id_event;

  @Id
  private UUID id_user;

  @ManyToOne
  @JoinColumn(name = "id_event", referencedColumnName = "id_event", insertable = false, updatable = false)
  private Event event;

  @ManyToOne
  @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
  private User user;

}
