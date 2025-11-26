package com.izaacpinheiro.api.controller;

import com.izaacpinheiro.api.domain.event.Event;
import com.izaacpinheiro.api.domain.event.EventRequestDTO;
import com.izaacpinheiro.api.domain.event.EventResponseDTO;
import com.izaacpinheiro.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event") // qual endpoint ele vai controlar
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventRequestDTO body) {
        Event newEvent = this.eventService.createEvent(body);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(allEvents);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> getFilteredEvents(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(required = false) String title,
                                                                    @RequestParam(required = false) String city,
                                                                    @RequestParam(required = false) String uf,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        List<EventResponseDTO> events = eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok(events);
    }
}
