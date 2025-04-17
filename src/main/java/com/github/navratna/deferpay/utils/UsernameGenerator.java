package com.github.navratna.deferpay.utils;

import com.github.navratna.deferpay.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class UsernameGenerator {

    private final UserRepository userRepository;

    public UsernameGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateUsername(String firstName) {
        String base = firstName.toLowerCase().trim();
        String suffix = Long.toString(ThreadLocalRandom.current().nextLong(2176782336L), 36);

        String username = base+suffix;

        int retries = 0;
        while(userRepository.existsByUsername(username)){
            retries++;
            suffix = Long.toString(ThreadLocalRandom.current().nextLong((long) Math.pow(36,6+retries)), 36);
            username = base+suffix;
        }

        return username;
    }
}
