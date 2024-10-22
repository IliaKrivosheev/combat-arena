package com.combat.arena.core.repository;

import com.combat.arena.core.organizer.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
    List<EventOrganizer> findEventOrganizersByName(String name);
    @Query(value = """
            SELECT * FROM event_organizer
            WHERE name = :name 
            LIMIT 1;
        """, nativeQuery = true)
    EventOrganizer findEventOrganizerByName(String name);
    EventOrganizer findEventOrganizerByUuid(String uuid);
    EventOrganizer findEventOrganizerById(Long id);
    Long countAllByName(String name);
}
