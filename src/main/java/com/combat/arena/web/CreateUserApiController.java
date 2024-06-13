package com.combat.arena.web;

import com.combat.arena.core.EventOrganizer;
import com.combat.arena.core.Role;
import com.combat.arena.core.User;
import com.combat.arena.core.repository.EventOrganizerRepository;
import com.combat.arena.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
public class CreateUserApiController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    public CreateUserApiController(UserRepository repository, PasswordEncoder passwordEncoder, EventOrganizerRepository eventOrganizerRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.eventOrganizerRepository = eventOrganizerRepository;
    }

    @PostMapping("api/new-user")
    public String addUser(@RequestBody String user) {
        log.debug("user: {}", user);
        JSONObject userJson = new JSONObject(user);
        String email = userJson.getString("email");
        String password = userJson.getString("password");
        Set<Role> userRoles = new HashSet<>();
        JSONArray rolesArray = userJson.getJSONArray("roles");
        for (int i = 0; i < rolesArray.length(); i++) {
            Role role = new Role(rolesArray.getString(i));
            userRoles.add(role);
        }
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setRoles(userRoles);
        newUser = repository.save(newUser);
        log.debug("newUser: {}", newUser);
        return "User is saved";
    }

    @PostMapping("api/new-event-organizer")
    public String addEventOrganizer(@RequestBody String eventOrganizer) {
        log.debug("eventOrganizer: {}", eventOrganizer);
        JSONObject eventOrganizerJson = new JSONObject(eventOrganizer);
        String name = eventOrganizerJson.getString("name");
        EventOrganizer newEventOrganizer = new EventOrganizer();
        newEventOrganizer.setName(name);
        newEventOrganizer = eventOrganizerRepository.save(newEventOrganizer);
        log.debug("newEventOrganizer: {}", newEventOrganizer);
        return "EventOrganizer is saved";
    }
}
