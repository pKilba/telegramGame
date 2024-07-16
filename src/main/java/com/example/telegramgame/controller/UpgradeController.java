package com.example.telegramgame.controller;

import com.example.telegramgame.entity.Upgrade;
import com.example.telegramgame.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/upgrades")
public class UpgradeController {

    @Autowired
    private UpgradeService upgradeService;

    @PostMapping("/save")
    public ResponseEntity<Upgrade> saveUpgrade(@RequestBody Upgrade upgrade) {
        Upgrade savedUpgrade = upgradeService.saveOrUpdateUpgrade(upgrade);
        return ResponseEntity.ok(savedUpgrade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Upgrade> getUpgradeById(@PathVariable Long id) {
        Optional<Upgrade> upgrade = upgradeService.getUpgradeById(id);
        return upgrade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Upgrade>> getAllUpgrades() {
        List<Upgrade> upgrades = upgradeService.getAllUpgrades();
        return ResponseEntity.ok(upgrades);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpgrade(@PathVariable Long id) {
        upgradeService.deleteUpgrade(id);
        return ResponseEntity.noContent().build();
    }
}
