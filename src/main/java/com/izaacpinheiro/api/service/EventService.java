package com.izaacpinheiro.api.service;

import com.izaacpinheiro.api.domain.event.Event;
import com.izaacpinheiro.api.domain.event.EventRequestDTO;
import com.izaacpinheiro.api.domain.event.EventResponseDTO;
import com.izaacpinheiro.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data) {

        // Crição do novo evento com as informações passadas pelo usuário
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setRemote(data.remote());// Conversão do time stamp do front para um Date

        repository.save(newEvent);

        return newEvent;
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.repository.findUpcomingEvents(new Date(), pageable);
        return eventsPage.map(event -> new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(), event.getDate(), "", "", event.getRemote(), event.getEventUrl()))
                .stream().toList();
    }
}
