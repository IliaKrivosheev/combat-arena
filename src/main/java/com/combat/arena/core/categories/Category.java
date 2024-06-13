package com.combat.arena.core.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.combat.arena.core.common.AbstractAuditingEntity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractAuditingEntity implements Serializable {

    @Column
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;

    @Column
    private Boolean team = false;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "age_category_id")
    @JsonIgnore
    private AgeCategory ageCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "weight_category_id")
    @JsonIgnore
    private WeightCategory weightCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column
    @NotNull
    private String eventOrganizerUuid;
}
