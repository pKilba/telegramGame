package com.example.telegramgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-score")
    public ResponseEntity<User> saveScore(@RequestBody User user) {
        System.out.println(user);
        User savedUser = userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/get-user/{telegramId}")
    public ResponseEntity<User> getUser(@PathVariable String telegramId) {
        Optional<User> user = userService.getUserByTelegramId(telegramId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/update-balance")
    public ResponseEntity<Wallet> updateBalance(@RequestParam String telegramId, @RequestParam Wallet.Currency currency, @RequestParam double amount) {
        try {
            Wallet updatedWallet = userService.updateBalance(telegramId, currency, amount);
            return ResponseEntity.ok(updatedWallet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get-balances/{telegramId}")
    public ResponseEntity<Set<Wallet>> getBalances(@PathVariable String telegramId) {
        try {
            Set<Wallet> wallets = userService.getBalances(telegramId);
            return ResponseEntity.ok(wallets);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
