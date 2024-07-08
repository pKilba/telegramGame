package com.example.telegramgame;

import com.example.telegramgame.User;
import com.example.telegramgame.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-score")
    public ResponseEntity<User> saveScore(@RequestBody User user) {
        User savedUser = userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/get-user/{telegramId}")
    public ResponseEntity<User> getUser(@PathVariable String telegramId) {
        Optional<User> user = userService.getUserByTelegramId(telegramId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}