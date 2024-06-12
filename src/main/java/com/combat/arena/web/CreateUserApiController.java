package com.combat.arena.web;

import com.combat.arena.core.Role;
import com.combat.arena.core.User;
import com.combat.arena.core.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class CreateUserApiController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserApiController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("api/new-user")
    public String addUser(@RequestBody String user) {
        System.out.println(user);
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
        System.out.println(newUser);
        return "User is saved";
    }
}
