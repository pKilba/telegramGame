package com.example.telegramgame.repository;

import com.example.telegramgame.entity.User;
import com.example.telegramgame.entity.UserUpgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserUpgradeRepository extends JpaRepository<UserUpgrade, Long> {
    List<UserUpgrade> findByUser(User user);
}
