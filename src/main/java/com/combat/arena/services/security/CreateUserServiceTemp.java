package com.combat.arena.services.security;

import com.combat.arena.core.repository.UserRepository;
import com.combat.arena.core.security.Role;
import com.combat.arena.core.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserServiceTemp {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String userEmail, String userPassword, String eventOrganizerUuid) {
        log.debug("createUser: {}", userEmail);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role(AuthoritiesConstants.USER));
        userRoles.add(new Role(AuthoritiesConstants.ADMIN));

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(userPassword));
        newUser.setEmail(userEmail);
        newUser.setRoles(userRoles);
        newUser.setEventOrganizerUuid(eventOrganizerUuid);
        newUser = repository.save(newUser);

        log.debug("newUser: {}", newUser);
    }
}
