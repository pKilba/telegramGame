package com.example.telegramgame;

import com.example.telegramgame.User;
import com.example.telegramgame.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user) {
        Optional<User> existingUser = userRepository.findByTelegramId(user.getTelegramId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setScore(user.getScore());
            return userRepository.save(updatedUser);
        } else {
            return userRepository.save(user);
        }
    }

    public Optional<User> getUserByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }
}
