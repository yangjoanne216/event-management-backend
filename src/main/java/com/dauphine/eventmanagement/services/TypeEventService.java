package com.dauphine.eventmanagement.services;

import com.dauphine.eventmanagement.models.TypeEvent;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TypeEventService {

  List<TypeEvent> getAllTypesEvent();
}
