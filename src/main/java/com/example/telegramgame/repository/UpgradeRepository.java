package com.example.telegramgame.repository;

import com.example.telegramgame.entity.Upgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpgradeRepository extends JpaRepository<Upgrade, Long> {
}
