package com.fsknso.competition.core.categories;

import com.fsknso.competition.core.common.AbstractAuditingEntity;
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
    private Double fromAge;

    @Column
    private Double toAge;

    @Column
    private Boolean minCategory = false;

    @Column
    private Boolean maxCategory = false;
}
