package com.dauphine.eventmanagement.repositories;

import com.dauphine.eventmanagement.models.Location;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
