package com.combat.arena.services.security;

import com.combat.arena.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUserService {
    private final UserRepository repository;

    public boolean alredyHasUserByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
