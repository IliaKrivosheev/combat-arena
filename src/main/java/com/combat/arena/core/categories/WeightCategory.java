package com.combat.arena.core.categories;

import com.combat.arena.core.common.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "weight_сategories")
@EqualsAndHashCode(callSuper = true)
public class WeightCategory extends AbstractAuditingEntity implements Serializable {

    @Column
    private String uuid = UUID.randomUUID().toString();

    @Column
    private Double fromWeight;

    @Column
    private Double toWeight;

    @Column
    private Boolean minCategory = false;

    @Column
    private Boolean maxCategory = false;

    @Column
    @NotNull
    private String eventOrganizerUuid;
}
