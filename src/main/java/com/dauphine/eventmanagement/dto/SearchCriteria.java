package com.dauphine.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SearchCriteria {

  private List<String> eventTypes;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<String> cities;
  private List<String> locationTypes;


  public SearchCriteria(List<String> eventTypes, LocalDateTime startDate, LocalDateTime endDate,
      List<String> cities, List<String> locationTypes) {
    this.eventTypes = eventTypes;
    this.startDate = startDate;
    this.endDate = endDate;
    this.cities = cities;
    this.locationTypes = locationTypes;
  }

  public List<String> getEventTypes() {
    return eventTypes;
  }

  public void setEventTypes(List<String> eventTypes) {
    this.eventTypes = eventTypes;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public List<String> getCities() {
    return cities;
  }

  public void setCities(List<String> cities) {
    this.cities = cities;
  }

  public List<String> getLocationTypes() {
    return locationTypes;
  }

  public void setLocationTypes(List<String> locationTypes) {
    this.locationTypes = locationTypes;
  }


}
