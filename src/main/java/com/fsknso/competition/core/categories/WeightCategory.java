package com.fsknso.competition.core.categories;

import com.fsknso.competition.core.common.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = -693004364284790253L;

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
}
