package com.example.telegramgame.service;

import com.example.telegramgame.entity.User;
import com.example.telegramgame.entity.UserUpgrade;
import com.example.telegramgame.repository.UpgradeRepository;
import com.example.telegramgame.repository.UserRepository;
import com.example.telegramgame.repository.UserUpgradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserUpgradeService {

    @Autowired
    private UserUpgradeRepository userUpgradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UpgradeRepository upgradeRepository;

    public UserUpgrade saveOrUpdateUserUpgrade(UserUpgrade userUpgrade) {
        return userUpgradeRepository.save(userUpgrade);
    }

    public Optional<UserUpgrade> getUserUpgradeById(Long id) {
        return userUpgradeRepository.findById(id);
    }

    public List<UserUpgrade> getUserUpgradesByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return user.map(userUpgradeRepository::findByUser).orElse(null);
    }

    public void deleteUserUpgrade(Long id) {
        userUpgradeRepository.deleteById(id);
    }
}
