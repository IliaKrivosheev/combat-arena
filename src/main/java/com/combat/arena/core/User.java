package com.combat.arena.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.combat.arena.core.common.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditingEntity implements Serializable {
    @NotNull
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Size(min = 5, max = 100)
    @Column(length = 100)
    private String password;

    @Column
    @NotNull
    private String eventOrganizerUuid;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserRoles",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleName", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();
}
