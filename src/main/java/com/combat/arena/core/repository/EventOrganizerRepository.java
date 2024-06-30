package com.combat.arena.core.repository;

import com.combat.arena.core.organizer.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
    List<EventOrganizer> findEventOrganizersByName(String name);
    EventOrganizer findEventOrganizerByUuid(String uuid);
    EventOrganizer findEventOrganizerById(Long id);

}
