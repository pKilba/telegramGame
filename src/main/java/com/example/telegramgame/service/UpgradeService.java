package com.example.telegramgame.service;


import com.example.telegramgame.entity.Upgrade;
import com.example.telegramgame.repository.UpgradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpgradeService {

    @Autowired
    private UpgradeRepository upgradeRepository;

    public Upgrade saveOrUpdateUpgrade(Upgrade upgrade) {
        return upgradeRepository.save(upgrade);
    }

    public Optional<Upgrade> getUpgradeById(Long id) {
        return upgradeRepository.findById(id);
    }

    public List<Upgrade> getAllUpgrades() {
        return upgradeRepository.findAll();
    }

    public void deleteUpgrade(Long id) {
        upgradeRepository.deleteById(id);
    }
}
