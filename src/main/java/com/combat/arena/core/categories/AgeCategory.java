package com.combat.arena.core.categories;

import com.combat.arena.core.common.AbstractAuditingEntity;
import com.combat.arena.services.security.utils.SecurityUtils;
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
@Table(name = "age_сategories")
@EqualsAndHashCode(callSuper = true)
public class AgeCategory extends AbstractAuditingEntity implements Serializable {

    @Column
    @NotNull
    private String name;

    @Column
    private String uuid = UUID.randomUUID().toString();

    @Column
    private Integer fromAge;

    @Column
    private Integer toAge;

    @Column
    private Boolean minCategory = false;

    @Column
    private Boolean maxCategory = false;

    @Column
    @NotNull
    private String eventOrganizerUuid = SecurityUtils.getCurrentOrganizerUuid();
}