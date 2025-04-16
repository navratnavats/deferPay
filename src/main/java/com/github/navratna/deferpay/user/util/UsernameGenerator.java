package com.github.navratna.deferpay.user.util;

import com.github.navratna.deferpay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class UsernameGenerator {

    private final UserRepository userRepository;

    public UsernameGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateUserName(String firstName) {
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
