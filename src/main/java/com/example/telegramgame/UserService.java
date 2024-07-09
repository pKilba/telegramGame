package com.example.telegramgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user) {
        Optional<User> existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setScore(user.getScore());
            updatedUser.setUserId(user.getUserId()); // Обновляем также userId
            return userRepository.save(updatedUser);
        } else {
            return userRepository.save(user);
        }
    }

    public Optional<User> getUserByTelegramId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
