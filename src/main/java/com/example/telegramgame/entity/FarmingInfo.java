package com.example.telegramgame.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "farming_info")
public class FarmingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime lastClaimTime;
    private int tokenIncrement;
    private int waitTime; // In seconds

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    public FarmingInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastClaimTime() {
        return lastClaimTime;
    }

    public void setLastClaimTime(LocalDateTime lastClaimTime) {
        this.lastClaimTime = lastClaimTime;
    }

    public int getTokenIncrement() {
        return tokenIncrement;
    }

    public void setTokenIncrement(int tokenIncrement) {
        this.tokenIncrement = tokenIncrement;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
