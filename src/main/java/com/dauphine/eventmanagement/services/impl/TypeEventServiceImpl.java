package com.dauphine.eventmanagement.services.impl;

import com.dauphine.eventmanagement.models.TypeEvent;
import com.dauphine.eventmanagement.repositories.TypeEventRepository;
import com.dauphine.eventmanagement.services.TypeEventService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TypeEventServiceImpl implements TypeEventService {

  TypeEventRepository typeEventRepository;

  public TypeEventServiceImpl(TypeEventRepository typeEventRepository) {
    this.typeEventRepository = typeEventRepository;
  }

  @Override
  public List<TypeEvent> getAllTypesEvent() {
    return typeEventRepository.findAll();
  }
}
