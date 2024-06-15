package com.combat.arena.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.combat.arena.core.common.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
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
public class User extends AbstractAuditingEntity implements Serializable, UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
