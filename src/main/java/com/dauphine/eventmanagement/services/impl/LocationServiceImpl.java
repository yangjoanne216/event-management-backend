package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.Location;
import com.dauphine.eventmanagement.repositories.LocationRepository;
import com.dauphine.eventmanagement.services.LocationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  public LocationServiceImpl(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public List<Location> getAllLocations() {
    return locationRepository.findAll();
  }
}
