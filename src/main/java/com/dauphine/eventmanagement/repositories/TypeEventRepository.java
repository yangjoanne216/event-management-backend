package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.TypeEvent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEventRepository extends JpaRepository<TypeEvent, UUID> {

}
