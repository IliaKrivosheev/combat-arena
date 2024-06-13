package com.combat.arena.core;

import com.combat.arena.core.common.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "event_organizer")
@EqualsAndHashCode(callSuper = true)
public class EventOrganizer extends AbstractAuditingEntity implements Serializable {
    @Column
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;
}
