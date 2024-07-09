package com.example.telegramgame;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "username")
    private String username;

    @Column(name = "user_id") // Используем snake_case для имени столбца
    private Integer userId;

    @Column(name = "score")
    private Integer score;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(Long id, String username, Integer userId) {
        this.id = id;
        this.username = username;
        this.userId = userId;
    }

    public User() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
