package com.example.telegramgame.repository;

import com.example.telegramgame.entity.FarmingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmingInfoRepository extends JpaRepository<FarmingInfo, Long> {
    FarmingInfo findByUserUserId(String telegramId);
}
