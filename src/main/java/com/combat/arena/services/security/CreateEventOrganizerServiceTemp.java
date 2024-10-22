package com.combat.arena.services.security;

import com.combat.arena.core.organizer.EventOrganizer;
import com.combat.arena.core.repository.EventOrganizerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateEventOrganizerServiceTemp {
    private final EventOrganizerRepository eventOrganizerRepository;

    private static final String FSKNSO = "fsknso";
    private EventOrganizer getFSKNSOOrganizer() {
        Long orgCount = eventOrganizerRepository.countAllByName(FSKNSO);
        return orgCount == 0L ? null : eventOrganizerRepository.findEventOrganizerByName(FSKNSO);
    }
    public EventOrganizer getDefaultOrganizer() {
        EventOrganizer defaultOrganizer = getFSKNSOOrganizer();
        if(defaultOrganizer != null) {
            log.debug("defaultOrganizer: {}", defaultOrganizer);
            return defaultOrganizer;
        }

        EventOrganizer newEventOrganizer = new EventOrganizer();
        newEventOrganizer.setName(FSKNSO);
        newEventOrganizer = eventOrganizerRepository.save(newEventOrganizer);
        log.debug("new DefaultOrganizer: {}", newEventOrganizer);
        return newEventOrganizer;
    }
}
