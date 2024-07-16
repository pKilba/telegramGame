package com.example.telegramgame.controller;


import com.example.telegramgame.entity.UserUpgrade;
import com.example.telegramgame.service.UserUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-upgrades")
public class UserUpgradeController {

    @Autowired
    private UserUpgradeService userUpgradeService;

    @PostMapping("/save")
    public ResponseEntity<UserUpgrade> saveUserUpgrade(@RequestBody UserUpgrade userUpgrade) {
        UserUpgrade savedUserUpgrade = userUpgradeService.saveOrUpdateUserUpgrade(userUpgrade);
        return ResponseEntity.ok(savedUserUpgrade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserUpgrade> getUserUpgradeById(@PathVariable Long id) {
        Optional<UserUpgrade> userUpgrade = userUpgradeService.getUserUpgradeById(id);
        return userUpgrade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserUpgrade>> getUserUpgradesByUserId(@PathVariable String userId) {
        List<UserUpgrade> userUpgrades = userUpgradeService.getUserUpgradesByUserId(userId);
        if (userUpgrades != null) {
            return ResponseEntity.ok(userUpgrades);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserUpgrade(@PathVariable Long id) {
        userUpgradeService.deleteUserUpgrade(id);
        return ResponseEntity.noContent().build();
    }
}
