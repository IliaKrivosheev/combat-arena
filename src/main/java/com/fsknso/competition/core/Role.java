package com.fsknso.competition.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements Serializable, GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 8514454790162550351L;

    @Id
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
