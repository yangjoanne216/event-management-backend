package com.dauphine.eventmanagement.controllers;

import com.dauphine.eventmanagement.models.Location;
import com.dauphine.eventmanagement.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/locations")
@Tag(
    name = "Location API",
    description = "Location endPoints"
)
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @RequestMapping
  @Operation(
      summary = "Retrieve all locations",
      description = "Fetches a list of all locations where events can be held."
  )
  public List<Location> getAllLocations() {
    List<Location> locations = locationService.getAllLocations();
    return locations;
  }
}
