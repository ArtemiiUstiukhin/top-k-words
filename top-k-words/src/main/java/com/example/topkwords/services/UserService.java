package com.example.topkwords.services;

import com.example.topkwords.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final String USER_EMAIL = "test@test.com";

    public Optional<UserEntity> findByEmail(String email) {
        // Fake user for simplification

        if (! USER_EMAIL.equalsIgnoreCase(email)) {
            return Optional.empty();
        }

        var user = new UserEntity();
        user.setId(1L);
        user.setEmail(USER_EMAIL);
        user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); // test
        user.setRole("ROLE_USER");
        user.setExtraInfo("Fake user");
        return Optional.of(user);
    }
}
